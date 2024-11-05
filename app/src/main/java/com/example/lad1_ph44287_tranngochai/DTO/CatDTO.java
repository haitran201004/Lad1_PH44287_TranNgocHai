package com.example.lad1_ph44287_tranngochai.DTO;

public class CatDTO {
    int id;
    String name;
    public String toString (){
        return "ID cat: "+ id + ", name: " + name;
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
//tạo các hàm getter vad setter bằng generate
}

