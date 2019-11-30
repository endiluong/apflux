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
import com.oldbie.apflux.model.MarkDetail;
import com.ramotion.foldingcell.FoldingCell;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MarkAdapter extends ArrayAdapter<Mark> {
    private Context context;
    private List<Mark> arrMark;
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();

    public MarkAdapter(Context context, ArrayList<Mark> arrMark) {
        super(context, 0, arrMark);
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.mark_item, parent, false);
//            viewHolder = new ViewHolder();
//            viewHolder.tvAVG = convertView.findViewById(R.id.tvAVG);
//            viewHolder.tvSubName = convertView.findViewById(R.id.tvSubName);
//            viewHolder.tvSubID = convertView.findViewById(R.id.tvSubID);
//            viewHolder.tvSeason = convertView.findViewById(R.id.tvSeason);
//            viewHolder.tvSubStatus = convertView.findViewById(R.id.tvSubStatus);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        Mark mark = arrMark.get(position);
//        viewHolder.tvAVG.setText(mark.getMark());
//        viewHolder.tvSubName.setText(mark.getSubjectName());
//        viewHolder.tvSubID.setText(mark.getSubjectId());
//        viewHolder.tvSeason.setText(mark.getSeason());
//        if (mark.getStatus().equals("1")) {
//            viewHolder.tvSubStatus.setText("Passed");
//            viewHolder.tvSubStatus.setTextColor(Color.parseColor("#00c853"));
//
//        } else {
//            viewHolder.tvSubStatus.setText("Failed");
//            viewHolder.tvSubStatus.setTextColor(Color.parseColor("#ff3d00"));
//        }
//        return convertView;

        // get item for selected view
        Mark item = getItem(position);
        MarkDetail itemDetail = getItem(position);

        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;

        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate( R.layout.cell, parent, false);

            //.. binding view parts to view holder ..//
            //.. Head ..//
            viewHolder.tvDate_head = cell.findViewById(R.id.tvDate_head);
            viewHolder.tvSlot_head = cell.findViewById(R.id.tvSlot_head);
            viewHolder.tvSubject_head = cell.findViewById(R.id.tvSubject_head);
            viewHolder.tvPlace_head = cell.findViewById(R.id.tvPlace_head);
            viewHolder.tvRoom_head = cell.findViewById(R.id.tvRoom_head);

            //.. Content ..//
            viewHolder.tvRoom_content = cell.findViewById(R.id.tvRoom_content);
            viewHolder.tvPlace_content = cell.findViewById(R.id.tvPlace_content);
            viewHolder.tvDate_content = cell.findViewById(R.id.tvDate_content);
            viewHolder.tvTutor_content = cell.findViewById(R.id.tvTutor_content);
            viewHolder.tvSubject_content = cell.findViewById(R.id.tvSubject_content);
            viewHolder.tvIdSubject_content = cell.findViewById(R.id.tvIdSubject_content);
            viewHolder.tvClass_content = cell.findViewById(R.id.tvClass_content);
            viewHolder.tvSlot_content = cell.findViewById(R.id.tvSlot_content);

            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        if (item == null) {
            return cell;
        }

        //.. bind data from selected element to view through view holder
        //.. head
        viewHolder.tvDate_head.setText(item.getSeason());
        viewHolder.tvSlot_head.setText(item.getSubjectName());
        viewHolder.tvSubject_head.setText(itemDetail.getMark());
        viewHolder.tvPlace_head.setText(item.getmPlace());
        viewHolder.tvRoom_head.setText(item.getmRoom());
        //.. content
        viewHolder.tvRoom_content.setText(item.getmRoom());
        viewHolder.tvPlace_content.setText(item.getmPlace());
        viewHolder.tvDate_content.setText(fmtOut.format(date));
        viewHolder.tvTutor_content.setText(item.getmTutor());
        viewHolder.tvSubject_content.setText(item.getmSubject());
        viewHolder.tvIdSubject_content.setText(item.getmIdSubject());
        viewHolder.tvClass_content.setText(item.getmClass());
        viewHolder.tvSlot_content.setText(item.getmSlot());
        //..
        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    private void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    private void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

//    public static class ViewHolder {
//        TextView tvAVG, tvSubName, tvSubID, tvSeason, tvSubStatus;
//    }

    // View lookup cache
    private static class ViewHolder {
        //.. Head ..//
        TextView tvDate_head;
        TextView tvSlot_head;
        TextView tvSubject_head;
        TextView tvPlace_head;
        TextView tvRoom_head;
        //.. Content ..//
        TextView tvRoom_content;
        TextView tvPlace_content;
        TextView tvDate_content;
        TextView tvTutor_content;
        TextView tvSubject_content;
        TextView tvIdSubject_content;
        TextView tvClass_content;
        TextView tvSlot_content;
    }
}