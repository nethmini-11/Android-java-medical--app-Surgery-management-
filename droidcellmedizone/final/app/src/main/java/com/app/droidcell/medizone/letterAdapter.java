package com.app.droidcell.medizone;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class letterAdapter extends ArrayAdapter<Letter>{

    Context context;
    int resource;

    public letterAdapter(@NonNull Context context, int resource, @NonNull List<Letter> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource, parent, false  );

        TextView tvNote = (TextView)convertView.findViewById(R.id.tvNote);
        ImageView imgUser = (ImageView) convertView.findViewById(R.id.imgUser);



        Letter currentLetter = getItem(position);

        tvNote.setText(currentLetter.getNote());
//




        Bitmap bitmap = BitmapFactory.decodeByteArray(currentLetter.getImage(), 0, currentLetter.getImage().length);
        imgUser.setImageBitmap(bitmap);


        return convertView;
    }
}
