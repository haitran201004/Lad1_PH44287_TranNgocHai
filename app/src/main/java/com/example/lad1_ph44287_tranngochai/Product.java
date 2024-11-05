package com.example.lad1_ph44287_tranngochai;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return v;
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
}