package com.techease.speedracerz.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techease.speedracerz.R;
import com.techease.speedracerz.dataModels.myTicketsModel.MyTicketsData;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyTicketsAdapter extends RecyclerView.Adapter<MyTicketsAdapter.MyViewHolder> {
    List<MyTicketsData> ticketsDataList;
    Context context;

    public MyTicketsAdapter(Context context, List<MyTicketsData> ticketsDataList) {
        this.ticketsDataList = ticketsDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_my_tickets_layout, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        final MyTicketsData ticketDataModel = ticketsDataList.get(i);
        Picasso.get().load(ticketDataModel.getEventimage()).into(holder.ivEvent);
        holder.tvEventType.setText(ticketDataModel.getEventCategory());
        holder.eventTitle.setText(ticketDataModel.getEventTitle());
        holder.tvDateTime.setText(ticketDataModel.getTicketBookedAt());
        holder.tvQuantity.setText(ticketDataModel.getNoOfTickets());


    }

    @Override
    public int getItemCount() {
        return ticketsDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView ivEvent;
        TextView tvEventType, eventTitle, tvDateTime, tvBookedBy, tvQuantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivEvent = itemView.findViewById(R.id.iv_my_tickets);
            tvEventType = itemView.findViewById(R.id.tv_race_type);
            tvDateTime = itemView.findViewById(R.id.tv_date_time);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            eventTitle = itemView.findViewById(R.id.tv_title);
            tvBookedBy = itemView.findViewById(R.id.tv_booked_by);
            tvDateTime = itemView.findViewById(R.id.tv_date_time);
        }
    }
}
