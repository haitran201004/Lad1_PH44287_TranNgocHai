package com.example.lad1_ph44287_tranngochai.DbHelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(Context context){
        super(context, "QLBH.db",null,5);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCategory = "CREATE TABLE tb_cat ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT  NOT NULL )";
        sqLiteDatabase.execSQL(sqlCategory);
        String sqlProduct = "CREATE TABLE tb_product (id INTEGER PRIMARY KEY, name TEXT NOT NULL,price REAL DEFAULT (0.0) NOT NULL ,id_cat INTEGER REFERENCES tb_cat (id))";
        String valueProduct = "INSERT INTO tb_product (id, name, price, id_cat) VALUES (1,'dien thoai Sámung', 2500000.0, 5),(2,'may tinh', 3500000.0, 2),(3, 'laptop', 1800000.0, 3)";
        sqLiteDatabase.execSQL(sqlProduct);
        sqLiteDatabase.execSQL(valueProduct);
        //Mỗi khi chỉnh sửa câu lệnh SQL ở trên thì tăng version ở hàm khởi tạo
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i1 > i){ // i1 là pban mới, i là phiên bản cũ
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_cat");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_product");
            onCreate(sqLiteDatabase);
        }
    }
}

