package com.example.lad1_ph44287_tranngochai.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lad1_ph44287_tranngochai.DAO.ProductDAO;
import com.example.lad1_ph44287_tranngochai.DTO.ProductDTO;
import com.example.lad1_ph44287_tranngochai.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private ArrayList<ProductDTO> list ;
    private Context context;
    private ProductDAO productDAO;


    public ProductAdapter(ArrayList<ProductDTO> list, Context context) {
        this.list = list;
        this.context = context;
        this.productDAO = new ProductDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(v);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductDTO productDTO = list.get(position);
        holder.tvidproduct.setText("ID: "+productDTO.getId());
        holder.tvnameproduct.setText("Name: "+productDTO.getName());
        holder.tvpriceproduct.setText("Price: "+productDTO.getPrice());
        holder.tvid_catproduct.setText("Category ID: "+productDTO.getId_cat());

        //sửa
        holder.btnEdit.setOnClickListener(v -> showEditDialog(productDTO, position));

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDeleted = productDAO.deleteProduct(productDTO.getId()); // Xóa trong cơ sở dữ liệu
                if (isDeleted) {
                    list.remove(position); // Xóa khỏi danh sách
                    notifyItemRemoved(position); // Cập nhật RecyclerView
                    notifyItemRangeChanged(position, list.size());
                    Toast.makeText(context, "Đã xóa sản phẩm", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Xóa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        if(list.size() == 0){
            return 0;
        }
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvnameproduct,tvpriceproduct,tvid_catproduct,tvidproduct;
        ImageButton btnDelete,btnEdit;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tvnameproduct = itemView.findViewById(R.id.tvnameproduct);
            tvpriceproduct= itemView.findViewById(R.id.tvpriceproduct);
            tvidproduct= itemView.findViewById(R.id.tvidproduct);
            tvid_catproduct= itemView.findViewById(R.id.tvid_catproduct);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnEdit = itemView.findViewById(R.id.btn_edit);

        }
    }
    private void showEditDialog(ProductDTO productDTO, int position) {
        // Tạo dialog từ layout
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.item_dialog_edit_product, null);
        builder.setView(dialogView);

        EditText etName = dialogView.findViewById(R.id.et_product_name);
        EditText etPrice = dialogView.findViewById(R.id.et_product_price);
        EditText etCategory = dialogView.findViewById(R.id.et_product_category);
        Button btnSave = dialogView.findViewById(R.id.btn_Luu);
        Button btnHuy = dialogView.findViewById(R.id.btn_Huy);

        // Gán giá trị hiện tại vào các EditText
        etName.setText(productDTO.getName());
        etPrice.setText(String.valueOf(productDTO.getPrice()));
        etCategory.setText(String.valueOf(productDTO.getId_cat()));

        // Tạo AlertDialog và hiển thị
        AlertDialog dialog = builder.create();
        dialog.show();
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(v -> {
            // Lấy giá trị mới từ các EditText
            String newName = etName.getText().toString();
            float newPrice = Float.parseFloat(etPrice.getText().toString());
            int newCategoryId = Integer.parseInt(etCategory.getText().toString());

            // Cập nhật thông tin sản phẩm
            productDTO.setName(newName);
            productDTO.setPrice(newPrice);
            productDTO.setId_cat(newCategoryId);

            // Cập nhật trong cơ sở dữ liệu và danh sách
            boolean isUpdated = productDAO.updateProduct(productDTO);
            if (isUpdated) {
                notifyItemChanged(position); // Cập nhật RecyclerView
                Toast.makeText(context, "Đã cập nhật sản phẩm", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        });
    }
}