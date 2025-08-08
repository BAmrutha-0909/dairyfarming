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

    private List<BuffaloModel> buffaloList;
    private Context context;
    private OnBuffaloClickListener listener;

    public interface OnBuffaloClickListener {
        void onBuffaloClick(BuffaloModel buffalo);
    }

    public BuffaloAdapter(Context context, List<BuffaloModel> buffaloList, OnBuffaloClickListener listener) {
        this.context = context;
        this.buffaloList = buffaloList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_buffalo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BuffaloModel buffalo = buffaloList.get(position);
        holder.name.setText(buffalo.getName());
        holder.milkProduction.setText("Milk: " + buffalo.getMilkProduction() + " L/day");
        holder.itemView.setOnClickListener(v -> listener.onBuffaloClick(buffalo));
    }

    @Override
    public int getItemCount() {
        return buffaloList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, milkProduction;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.buffaloName);
            milkProduction = itemView.findViewById(R.id.buffaloMilkProduction);
        }
    }
}
