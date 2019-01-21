package com.project.pentacode.pomestaff;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.pentacode.pomestaff.model.Notifikasi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    ArrayList<Notifikasi> notifikasis;
    Context context;

    public NotificationAdapter(ArrayList<Notifikasi> notifikasis, Context context) {
        this.notifikasis = notifikasis;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notifikasi notifikasi = notifikasis.get(position);
        holder.textNotifikasi.setText("Task "+notifikasi.getTask()+" menunggu review. ("+notifikasi.getProject()+" => "+notifikasi.getStep()+")");
    }

    @Override
    public int getItemCount() {
        return notifikasis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_notifikasi)
        TextView textNotifikasi;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
