package com.example.lad1_ph44287_tranngochai.DbHelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(Context context){
        super(context, "QLBH.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCategory = "CREATE TABLE tb_cat (\n" +
                "    id   INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name TEXT    UNIQUE\n" +
                "                 NOT NULL\n" +
                ");\n";
        sqLiteDatabase.execSQL(sqlCategory);
        String sqlProduct = "CREATE TABLE tb_product (\n" +
                "    id     INTEGER PRIMARY KEY,\n" +
                "    name   TEXT    UNIQUE\n" +
                "                   NOT NULL,\n" +
                "    price  REAL    DEFAULT (0.0) \n" +
                "                   NOT NULL,\n" +
                "    id_cat INTEGER REFERENCES tb_cat (id) \n" +
                ");\n";
        sqLiteDatabase.execSQL(sqlProduct);
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
