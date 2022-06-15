package com.app.droidcell.medizone;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;



public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList patient_id,patient_pid,patient_name,patient_con,patient_typ,patient_phone,patient_trustee;

    CustomAdapter(Activity activity, Context context, ArrayList patient_id, ArrayList patient_pid, ArrayList patient_name,
                  ArrayList patient_con,ArrayList patient_typ,ArrayList patient_phone,ArrayList patient_trustee){
        this.activity = activity;
        this.context = context;
        this.patient_id = patient_id;
        this.patient_pid = patient_pid;
        this.patient_name = patient_name;
        this.patient_con = patient_con;
        this.patient_typ = patient_typ;
        this.patient_phone = patient_phone;
        this.patient_trustee = patient_trustee;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.patient_id_txt.setText(String.valueOf(patient_id.get(position)));
        holder.patient_pid_txt.setText(String.valueOf(patient_pid.get(position)));
        holder.patient_name_txt.setText(String.valueOf(patient_name.get(position)));
        holder.patient_dob_txt.setText(String.valueOf(patient_con.get(position)));
        holder.patient_address_txt.setText(String.valueOf(patient_typ.get(position)));
        holder.patient_phone_txt.setText(String.valueOf(patient_phone.get(position)));
        holder.patient_trustee_txt.setText(String.valueOf(patient_trustee.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(patient_id.get(position)));
                intent.putExtra("pid", String.valueOf(patient_pid.get(position)));
                intent.putExtra("fname", String.valueOf(patient_name.get(position)));
                intent.putExtra("con", String.valueOf(patient_con.get(position)));
                intent.putExtra("typ", String.valueOf(patient_typ.get(position)));
                intent.putExtra("phone", String.valueOf(patient_phone.get(position)));
                intent.putExtra("trustee", String.valueOf(patient_trustee.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return patient_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView patient_id_txt,patient_pid_txt,patient_name_txt,patient_dob_txt,patient_address_txt,patient_phone_txt,patient_trustee_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            patient_id_txt = itemView.findViewById(R.id.patient_id_txt);
            patient_pid_txt = itemView.findViewById(R.id.patient_pid_txt);
            patient_name_txt = itemView.findViewById(R.id.patient_name_txt);
            patient_dob_txt= itemView.findViewById(R.id.patient_con_txt);
            patient_address_txt = itemView.findViewById(R.id.patient_typ_txt);
            patient_phone_txt = itemView.findViewById(R.id.patient_phone_txt);
            patient_trustee_txt= itemView.findViewById(R.id.patient_trustee_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
