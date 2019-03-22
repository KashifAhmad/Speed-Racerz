package com.techease.speedracerz.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techease.speedracerz.R;
import com.techease.speedracerz.dataModels.eventsDataModels.EventDataModel;
import com.techease.speedracerz.utils.SharedPrefUtils;
import com.techease.speedracerz.views.AboutEventsActivity;

import java.util.ArrayList;

import javax.xml.transform.Templates;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    ArrayList<EventDataModel> eventDataModelArrayList;
    Context context;

    public EventAdapter(Context context, ArrayList<EventDataModel> eventDataModelArrayList) {
        this.eventDataModelArrayList = eventDataModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_events_layout, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        final EventDataModel eventDataModel = eventDataModelArrayList.get(i);

        Picasso.get().load(eventDataModel.getImage()).into(holder.ivEvent);
        holder.eventTitle.setText(eventDataModel.getTitle());
        holder.tvDescription.setText(eventDataModel.getDescription());
        holder.tvDateTime.setText(eventDataModel.getEventDate()+"  |  " +eventDataModel.getEventTime());
        holder.ivEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefUtils.getEditor(context).putInt("event_id", eventDataModel.getId()).commit();
                context.startActivity(new Intent(context, AboutEventsActivity.class));

            }
        });


    }

    @Override
    public int getItemCount() {
        return eventDataModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivEvent;
        TextView tvDescription, eventTitle, tvDateTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivEvent = itemView.findViewById(R.id.iv_event);
            tvDescription = itemView.findViewById(R.id.tv_event_description);
            eventTitle = itemView.findViewById(R.id.tv_event_title);
            tvDateTime = itemView.findViewById(R.id.tv_date_time);
        }
    }
}
