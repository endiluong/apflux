package com.oldbie.apflux.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.oldbie.apflux.R;
import com.oldbie.apflux.model.Mark;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MarkAdapter extends ArrayAdapter<Mark> {

    private Context context;
    private int resource;
    private List<Mark> arrMark;

    public MarkAdapter(Context context, int resource, ArrayList<Mark> arrMark) {
        super(context, resource, arrMark);
        this.context = context;
        this.resource = resource;
        this.arrMark = arrMark;
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.mark_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvAVG = (TextView) convertView.findViewById(R.id.tvAVG);
            viewHolder.tvSubName = (TextView) convertView.findViewById(R.id.tvSubName);
            viewHolder.tvSubID = (TextView) convertView.findViewById(R.id.tvSubID);
            viewHolder.tvSeason = (TextView) convertView.findViewById(R.id.tvSeason);
            viewHolder.tvSubStatus = (TextView) convertView.findViewById(R.id.tvSubStatus);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Mark mark = arrMark.get(position);
        viewHolder.tvAVG.setText(mark.getMark());
        viewHolder.tvSubName.setText(mark.getSubjectName());
        viewHolder.tvSubID.setText(mark.getSubjectId());
        viewHolder.tvSeason.setText(mark.getSeason());
//        viewHolder.tvStatus.setText(mark.getStatus());
        if (mark.getStatus().equals("1")) {
            viewHolder.tvSubStatus.setText("Passed");
            viewHolder.tvSubStatus.setTextColor(Color.GREEN);

        } else {
            viewHolder.tvSubStatus.setText("Failed");
            viewHolder.tvSubStatus.setTextColor(Color.RED);
        }
        return convertView;
    }

    public static class ViewHolder {
        TextView tvAVG, tvSubName, tvSubID, tvSeason, tvSubStatus;
    }
}