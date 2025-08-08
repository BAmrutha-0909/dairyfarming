package com.example.dairyfarming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BuffaloAdapter extends RecyclerView.Adapter<BuffaloAdapter.ViewHolder> {

    public interface OnBuffaloClickListener {
        void onClick(BuffaloModel buffalo);
        void onLongClick(BuffaloModel buffalo);
    }

    private Context context;
    private List<BuffaloModel> buffaloList;
    private OnBuffaloClickListener listener;

    public BuffaloAdapter(Context context, List<BuffaloModel> buffaloList, OnBuffaloClickListener listener) {
        this.context = context;
        this.buffaloList = buffaloList;
        this.listener = listener;
    }

    public void updateList(List<BuffaloModel> newList) {
        buffaloList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BuffaloAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_buffalo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuffaloAdapter.ViewHolder holder, int position) {
        BuffaloModel buffalo = buffaloList.get(position);
        holder.nameTextView.setText(buffalo.getName());
        holder.litersTextView.setText(buffalo.getMilkProduction());

        holder.itemView.setOnClickListener(v -> listener.onClick(buffalo));
        holder.itemView.setOnLongClickListener(v -> {
            listener.onLongClick(buffalo);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return buffaloList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, litersTextView;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.buffaloNameTextView);
            litersTextView = itemView.findViewById(R.id.buffaloLitersTextView);
        }
    }
}
