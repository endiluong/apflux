package com.oldbie.apflux.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.oldbie.apflux.R;
import com.oldbie.apflux.model.GetMark;
import com.oldbie.apflux.model.Mark;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MarkAdapter extends ArrayAdapter<GetMark> {
    private Context context;
    private ArrayList<GetMark> arrMark;

    public MarkAdapter(Context context, ArrayList<GetMark> arrMark) {
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
        GetMark mark = arrMark.get(position);
        viewHolder.tvAVG.setText(mark.getmAverageGrade());
        viewHolder.tvSubName.setText(mark.getmSubjectName());
        viewHolder.tvSubID.setText(mark.getmSubjectId());
        viewHolder.tvSeason.setText(mark.getmSeason());
        if (mark.getmStatus().equals("1")) {
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