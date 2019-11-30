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
    private List<Mark> arrMark;

    public MarkAdapter(Context context, ArrayList<Mark> arrMark) {
        super(context, 0, arrMark);
        this.context = context;
        this.arrMark = arrMark;
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.mark_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvAVG = convertView.findViewById(R.id.tvAVG);
            viewHolder.tvSubName = convertView.findViewById(R.id.tvSubName);
            viewHolder.tvSubID = convertView.findViewById(R.id.tvSubID);
            viewHolder.tvSeason = convertView.findViewById(R.id.tvSeason);
            viewHolder.tvSubStatus = convertView.findViewById(R.id.tvSubStatus);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Mark mark = arrMark.get(position);
        viewHolder.tvAVG.setText(mark.getMark());
        viewHolder.tvSubName.setText(mark.getSubjectName());
        viewHolder.tvSubID.setText(mark.getSubjectId());
        viewHolder.tvSeason.setText(mark.getSeason());
        if (mark.getStatus().equals("1")) {
            viewHolder.tvSubStatus.setText("Passed");
            viewHolder.tvSubStatus.setTextColor(Color.parseColor("#00c853"));

        } else {
            viewHolder.tvSubStatus.setText("Failed");
            viewHolder.tvSubStatus.setTextColor(Color.parseColor("#ff3d00"));
        }
        return convertView;
    }

    public static class ViewHolder {
        TextView tvAVG, tvSubName, tvSubID, tvSeason, tvSubStatus;
    }
}