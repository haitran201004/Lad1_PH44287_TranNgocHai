package com.example.lad1_ph44287_tranngochai.DTO;

public class ProductDTO {
    int id;
    String name;
    float price;
    int id_cat;

    public String toString (){
        return "ID product: "+ id + ", name: " + name + ", price: " + price + ", id_cat: " +id_cat;
    }


    public ProductDTO() {
    }

    public ProductDTO(int id, String name, float price, int id_cat) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.id_cat = id_cat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }
}
