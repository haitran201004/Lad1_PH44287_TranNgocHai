package com.example.lad1_ph44287_tranngochai;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lad1_ph44287_tranngochai.Adapter.ProductAdapter;
import com.example.lad1_ph44287_tranngochai.DAO.ProductDAO;
import com.example.lad1_ph44287_tranngochai.DTO.ProductDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Product extends Fragment {

    ProductDTO productDTO;
    ProductAdapter productAdapter;
    ProductDAO productDAO;
    ArrayList<ProductDTO> list = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton actionButton;

    public Product() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product, container, false);

        // Inflate the layout for this fragment

        AnhXa(v);
        XuLySuKien();
        return v;
    }
    private void XuLySuKien(){
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });
    }

    private void AnhXa(View  v){
        recyclerView = v.findViewById(R.id.lv_product);
        actionButton = v.findViewById(R.id.btn_add);
        productDTO = new ProductDTO();
        productDAO = new ProductDAO(getContext());
        list = productDAO.getList();
        productAdapter = new ProductAdapter(list, getContext());
        recyclerView.setAdapter(productAdapter);

    }
    private void showAddDialog() {
        // Tạo dialog từ layout
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.item_dialog_edit_product, null);
        builder.setView(dialogView);

        EditText etName = dialogView.findViewById(R.id.et_product_name);
        EditText etPrice = dialogView.findViewById(R.id.et_product_price);
        EditText etCategory = dialogView.findViewById(R.id.et_product_category);
        Button btnSave = dialogView.findViewById(R.id.btn_Luu);
        Button btnHuy = dialogView.findViewById(R.id.btn_Huy);


        // Gán giá trị hiện tại vào các EditText
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


            int isADD = productDAO.addRow(productDTO);
            if (isADD > 0) {
                list.clear();
                list.addAll(productDAO.getList());
                productAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Đã thêm sản phẩm", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
            }


            dialog.dismiss();
        });
    }

}