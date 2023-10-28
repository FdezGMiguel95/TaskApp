package com.example.dutyapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.Duty;
import com.example.taskapp.R;

import java.util.ArrayList;

public class DutyAdapter extends RecyclerView.Adapter<DutyAdapter.ViewHolderDuty> {
    ArrayList<Duty> quereseres;

    public DutyAdapter(ArrayList<Duty> quereseres) {
        this.quereseres = quereseres;
    }

    @Override
    public ViewHolderDuty onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater lInflater= LayoutInflater.from(parent.getContext());
        View listCard = lInflater.inflate(R.layout.duty_card, parent, false);
        ViewHolderDuty vHolder = new ViewHolderDuty(listCard);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderDuty holder, @SuppressLint("RecyclerView") int position) {
        final Duty t = quereseres.get(position);
        holder.tvNameT.setText(quereseres.get(position).getDutyName());
        holder.tvdescT.setText(quereseres.get(position).getDutyDesc());
        holder.tvTypeT.setText(quereseres.get(position).getDutyType());
        if ( t.getFlag() == 0){
            holder.ivPicture.setImageResource(quereseres.get(position).getDutyDefImg());
        } else if (t.getFlag() == 1) {
            holder.ivPicture.setImageBitmap(quereseres.get(position).getDutyImg());
        }

    }

    @Override
    public int getItemCount() {
        return quereseres.size();
    }

    public class ViewHolderDuty extends RecyclerView.ViewHolder{
        public ImageView ivPicture;
        public TextView tvNameT, tvdescT, tvTypeT;
        public CheckBox cbComplete;

        public ViewHolderDuty(@NonNull View itemView) {
            super(itemView);
            this.ivPicture = (ImageView) itemView.findViewById(R.id.ivPicture);
            this.tvNameT = (TextView) itemView.findViewById(R.id.tvNameT);
            this.tvdescT = (TextView) itemView.findViewById(R.id.tvdescT);
            this.tvTypeT = (TextView) itemView.findViewById(R.id.tvTypeT);
            this.cbComplete = (CheckBox) itemView.findViewById(R.id.cbComplete);
        }
    }
}
