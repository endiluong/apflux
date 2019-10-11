package com.oldbie.applux.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.oldbie.apflux.R;
import com.oldbie.applux.model.TimeTable;
import com.ramotion.foldingcell.FoldingCell;

import java.util.HashSet;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TimeTableAdapter extends ArrayAdapter<TimeTable> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;

    public TimeTableAdapter(Context context, List<TimeTable> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get item for selected view
        TimeTable item = getItem( position );

        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;

        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate( R.layout.cell, parent, false);

            // binding view parts to view holder
            viewHolder.tvDayOfweek = cell.findViewById( R.id.tvDayOfWeek );
            viewHolder.tvClock = cell.findViewById( R.id.tvClock );
            viewHolder.tvTitle = cell.findViewById( R.id.tvTitle );
            viewHolder.tvLesson = cell.findViewById( R.id.tvLesson );
            viewHolder.tvTeacher = cell.findViewById( R.id.tvTeacher );
            viewHolder.tvRoom = cell.findViewById( R.id.tvRoom );
//            tvIdRoom = itemView.findViewById( R.id.tvId );
            viewHolder.tvIdSubject = cell.findViewById( R.id.tvIdSubject );
            viewHolder.tvSubject = cell.findViewById( R.id.tvSubject );
            viewHolder.tvSubjectDetail = cell.findViewById( R.id.tvSubjectDetail );
            viewHolder.contentRequestBtn = cell.findViewById(R.id.content_request_btn);

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

        if (null == item)
            return cell;

        // bind data from selected element to view through view holder
        viewHolder.tvDayOfweek.setText(item.getDayOfWeek());
        viewHolder.tvClock.setText(item.getClock());
        viewHolder.tvTitle.setText(item.getTitle());
        viewHolder.tvLesson.setText(item.getLesson());
        viewHolder.tvTeacher.setText(item.getTeacher());
        viewHolder.tvRoom.setText(item.getRoom());

        viewHolder.tvSubject.setText(item.getSubject());
        viewHolder.tvIdSubject.setText(item.getId_subject());
        viewHolder.tvSubjectDetail.setText(item.getSubject_detail());



        // set custom btn handler for list item from that item
        if (item.getRequestBtnClickListener() != null) {
            viewHolder.contentRequestBtn.setOnClickListener(item.getRequestBtnClickListener());
        } else {
            // (optionally) add "default" handler if no handler found in item
            viewHolder.contentRequestBtn.setOnClickListener(defaultRequestBtnClickListener);
        }

        return cell;
    }
    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView tvDayOfweek;
        TextView tvClock;
        TextView tvTitle;
        TextView tvLesson;
        TextView tvTeacher;
        TextView tvRoom;
        //        public TextView tvIdRoom;
        TextView tvIdSubject;
        TextView tvSubject;
        TextView tvSubjectDetail;
        TextView contentRequestBtn;

    }
}
