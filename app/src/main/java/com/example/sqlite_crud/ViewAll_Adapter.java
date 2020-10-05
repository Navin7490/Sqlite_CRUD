package com.example.sqlite_crud;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAll_Adapter extends RecyclerView.Adapter<ViewAll_Adapter.viewholder> {
    DataBaseHelper db;
    private ArrayList<ViewAll_Modal>product;
    private Context context;
    private String name="Name :";
    private String email="Email :";
    private String mobile="Mobile :";
    private String gender="Gender :";

    public ViewAll_Adapter(ArrayList<ViewAll_Modal> product, Context context) {
        this.product = product;
        this.context = context;
        db=new DataBaseHelper(context);
    }


    @NonNull
    @Override
    public ViewAll_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View viewlist=layoutInflater.inflate(R.layout.view_all_list,parent,false);

        return new viewholder(viewlist);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAll_Adapter.viewholder holder, int position) {
        holder.tvid.setText(product.get(position).getId());
        holder.tvname.setText(name.concat(product.get(position).getName()));
        holder.tvemail.setText(email.concat(product.get(position).getEmail()));
        holder.tvmobile.setText(mobile.concat(product.get(position).getMobile()));
        holder.tvgender.setText(gender.concat(product.get(position).getGender()));
        byte[]image=product.get(position).getImage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
        holder.imageView.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvid, tvname,tvemail,tvmobile,tvgender,tvdelete;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.Im_viewall);
            tvid=itemView.findViewById(R.id.Id);
            tvname=itemView.findViewById(R.id.Name);
            tvemail=itemView.findViewById(R.id.Email);
            tvmobile=itemView.findViewById(R.id.Mobile);
            tvgender=itemView.findViewById(R.id.Gender);
            tvdelete=itemView.findViewById(R.id.Delete);
            tvdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id=tvid.getText().toString();
                   Integer deid= db.DeleteData(id);
                   if (deid>0){
                       Toast.makeText(context, "deleted data", Toast.LENGTH_SHORT).show();
                       context.startActivity(new Intent(context.getApplicationContext(),MainActivity.class));


                   }
                   else {
                       Toast.makeText(context, "No delete", Toast.LENGTH_SHORT).show();

                   }

                }
            });
        }
    }
}
