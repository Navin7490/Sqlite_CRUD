package com.example.sqlite_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateData_Activity extends AppCompatActivity {
    DataBaseHelper db;
  TextView tvid;
  EditText etname,etemail,etmobile,etgender;
  Button btnupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_);
        db=new DataBaseHelper(getApplicationContext());
        tvid=findViewById(R.id.Id);
        etname=findViewById(R.id.Name);
        etemail=findViewById(R.id.Email);
        etmobile=findViewById(R.id.Mobile);
        etgender=findViewById(R.id.Gender);
        btnupdate=findViewById(R.id.Btn_Update);
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        String name=intent.getStringExtra("name");
        String email=intent.getStringExtra("email");
        String mobile=intent.getStringExtra("mobile");
        String gender=intent.getStringExtra("gender");

        tvid.setText(id);
        etname.setText(name);
        etemail.setText(email);
        etmobile.setText(mobile);
        etgender.setText(gender);



        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id,name,email,mobile,gender;
                id=tvid.getText().toString();
                name=etname.getText().toString();
                email=etemail.getText().toString();
                mobile=etmobile.getText().toString();
                gender=etgender.getText().toString();
             if (id.isEmpty()||name.isEmpty()||email.isEmpty()||mobile.isEmpty()||gender.isEmpty()){
                 Toast.makeText(UpdateData_Activity.this, "Field Required", Toast.LENGTH_SHORT).show();
             }else {
                 db.UpdateData(id,name,email,mobile,gender);
                 Toast.makeText(UpdateData_Activity.this, "data updated", Toast.LENGTH_SHORT).show();


             }
            }
        });
    }
}