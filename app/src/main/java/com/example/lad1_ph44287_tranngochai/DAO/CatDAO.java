package com.example.lad1_ph44287_tranngochai.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lad1_ph44287_tranngochai.DTO.CatDTO;
import com.example.lad1_ph44287_tranngochai.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class CatDAO {
    MyDbHelper dbHelper;
    SQLiteDatabase db;
    CatDTO objCurrentCat = null;
    public CatDAO(Context context){
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //ham them du lieu
    public int addRow (CatDTO objCat){
        ContentValues v = new ContentValues();
        v.put("name", objCat.getName());
        int kq = (int)db.insert("tb_cat",null,v);
        //kq: a nếu kq>0 thì đó là ID của bản ghi mới sinh ra do cơ chế tự động tăng
        return kq;
    }
    //hàm lấy ds
    public ArrayList<CatDTO> getList(){
        ArrayList<CatDTO> listCat = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT id, name FROM tb_cat", null);
        if(c != null && c.getCount()>0){
            //lay dc du lieu
            c.moveToFirst();
            do {
                //duyet vong lap
                //thu tu cot : id la 0, name la 1
                int id = c.getInt(0);
                String name = c.getString(1);
                CatDTO objCat = new CatDTO();
                objCat.setId(id);
                objCat.setName(name);
                //cho vao list
                listCat.add(objCat);
            }while (c.moveToNext());



        }else{
            //log: khong lay dc du lieu
            Log.d("zzzzzz","CatDAO::getList: Khong lay duoc du lieu");
        }
        return  listCat;
    }

    public CatDTO getOneById(int id){
        String [] params = {String.valueOf(id)};
        CatDTO objCat = null;
        Cursor c = db.rawQuery("SELECT id, name FROM tb_cat WHERE id = ?" , params);
        if(c != null && c.getCount()==1){
            objCat = new CatDTO();//khởi tạo đối tượng
            objCat.setId( c.getInt(0));
            objCat.setName(c.getString(1));
        }
        return objCat;
    }
    public boolean updateRow(CatDTO objCat){
        String [] dieu_kien = {String.valueOf(objCat.getId())};

        ContentValues v = new ContentValues();
        v.put("name", objCat.getName());
        //thực hiện lệnh cập nhật
        long kq = db.update("tb_cat ", v , "id = ?", dieu_kien);
        return kq > 0 ;//nếu update thành công thì kq >0
    }
    public boolean deleteRow (CatDTO objCat){
        // tạo đk update
        String [] dieu_kien = { String.valueOf(  objCat.getId()  ) };
        long kq = db.delete("tb_cat", "id = ?", dieu_kien );
        return kq>0;
    }
}
