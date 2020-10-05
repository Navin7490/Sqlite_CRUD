package com.example.sqlite_crud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper db;
    EditText etname, etemail, etpassword, etmobile;
    Button btnrst, btnlogin;
    RadioButton rbtnmale, rbtnfemal;
    String name, email, password, mobile;
    String gender = "";
    ImageView imageView;
 private static  final int PICKE_IMAGE=1;
 Bitmap bitmap;
 Uri imageuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBaseHelper(getApplicationContext());
        etemail = findViewById(R.id.Et_Email);
        etname = findViewById(R.id.Et_Name);
        etmobile = findViewById(R.id.Et_Mobile);
        etpassword = findViewById(R.id.Et_Password);
        rbtnmale = findViewById(R.id.Rbtn_Male);
        rbtnfemal = findViewById(R.id.Rbtn_Femal);
        btnrst = findViewById(R.id.Btn_Rst);
        btnlogin = findViewById(R.id.Btn_G_login);
        imageView=findViewById(R.id.Im_rst);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"select iamge"),PICKE_IMAGE);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login_Activity.class));
            }
        });

        btnrst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = etname.getText().toString();
                email = etemail.getText().toString();
                password = etpassword.getText().toString();
                mobile = etmobile.getText().toString();
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || mobile.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Required filled", Toast.LENGTH_SHORT).show();
                } else if (!rbtnmale.isChecked() && !rbtnfemal.isChecked()) {
                    Toast.makeText(MainActivity.this, "selcet gender", Toast.LENGTH_SHORT).show();

                } else {
                    if (rbtnmale.isChecked()) {
                        gender = rbtnmale.getText().toString();
                    } else {
                        gender = rbtnfemal.getText().toString();
                    }
                    db.InsertData(name, email, password, mobile, gender,ImagetoByte(imageView));
                    Toast.makeText(MainActivity.this, "Recorde insert", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    public void viewAlldata(View view) {
        startActivity(new Intent(getApplicationContext(),ViewAll_Activity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICKE_IMAGE&&resultCode==RESULT_OK){

            imageuri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(imageuri);
                bitmap=BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private byte[] ImagetoByte(ImageView image){
         bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte[]imagebyte=outputStream.toByteArray();
        return imagebyte;
    }
}