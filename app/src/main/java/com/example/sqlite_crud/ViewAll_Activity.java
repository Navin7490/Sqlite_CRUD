package com.example.sqlite_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewAll_Activity extends AppCompatActivity {
  RecyclerView recyclerView;
  ArrayList<ViewAll_Modal>product;
  DataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_);
        db=new DataBaseHelper(getApplicationContext());
        recyclerView=findViewById(R.id.Rv_ViewAll);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        product=new ArrayList<>();
       Cursor cursor= db.ViewAllData();
    if (cursor.getCount()==0){
        Toast.makeText(this, "No Record found", Toast.LENGTH_SHORT).show();
    }
  else {
      while (cursor.moveToNext()){
          ViewAll_Modal modal=new ViewAll_Modal();
          modal.setId(cursor.getString(0));
          modal.setName(cursor.getString(1));
          modal.setEmail(cursor.getString(2));
          modal.setMobile(cursor.getString(4));
          modal.setGender(cursor.getString(5));
          modal.setImage(cursor.getBlob(6));

          product.add(modal);


      }
        ViewAll_Adapter adapter=new ViewAll_Adapter(product, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    }
}