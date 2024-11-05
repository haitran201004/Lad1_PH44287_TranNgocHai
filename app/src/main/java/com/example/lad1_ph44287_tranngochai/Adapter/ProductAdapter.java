package com.example.lad1_ph44287_tranngochai.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lad1_ph44287_tranngochai.DTO.ProductDTO;
import com.example.lad1_ph44287_tranngochai.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private ArrayList<ProductDTO> list ;
    private Context context;


    public ProductAdapter(ArrayList<ProductDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductDTO productDTO = list.get(position);
        holder.tvidproduct.setText(productDTO.getId()+ "");
        holder.tvnameproduct.setText(productDTO.getName());
        holder.tvpriceproduct.setText(productDTO.getPrice()+ "");
        holder.tvid_catproduct.setText(productDTO.getId_cat()+"");

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
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tvnameproduct = itemView.findViewById(R.id.tvnameproduct);
            tvpriceproduct= itemView.findViewById(R.id.tvpriceproduct);
            tvidproduct= itemView.findViewById(R.id.tvidproduct);
            tvid_catproduct= itemView.findViewById(R.id.tvid_catproduct);

        }
    }
}