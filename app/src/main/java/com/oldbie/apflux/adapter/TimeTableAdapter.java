package com.oldbie.apflux.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.oldbie.apflux.R;
import com.oldbie.apflux.model.TimeTable;
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

            //.. binding view parts to view holder ..//
            //.. Head ..//
            viewHolder.tvDate_head =cell.findViewById( R.id.tvDate_head );
            viewHolder.tvSlot_head =cell.findViewById( R.id.tvSlot_head );
            viewHolder.tvSubject_head =cell.findViewById( R.id.tvSubject_head );
            viewHolder.tvPlace_head =cell.findViewById( R.id.tvPlace_head );
            viewHolder.tvRoom_head =cell.findViewById( R.id.tvRoom_head );

            //.. Content ..//
            viewHolder.tvRoom_content =cell.findViewById( R.id.tvRoom_content );
            viewHolder.tvPlace_content =cell.findViewById( R.id.tvPlace_content );
            viewHolder.tvDate_content =cell.findViewById( R.id.tvDate_content );
            viewHolder.tvTutor_content =cell.findViewById( R.id.tvTutor_content );
            viewHolder.tvSubject_content =cell.findViewById( R.id.tvSubject_content );

            viewHolder.tvIdSubject_content =cell.findViewById( R.id.tvIdSubject_content );
            viewHolder.tvClass_content =cell.findViewById( R.id.tvClass_content );
            viewHolder.tvSlot_content =cell.findViewById( R.id.tvSlot_content );
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

        //.. bind data from selected element to view through view holder
        //.. head
        viewHolder.tvDate_head.setText(item.getmDate());
        viewHolder.tvSlot_head.setText(item.getmSlot());
        viewHolder.tvSubject_head.setText(item.getmSubject());
        viewHolder.tvPlace_head.setText(item.getmPlace());
        viewHolder.tvRoom_head.setText(item.getmRoom());

        //.. content
        viewHolder.tvRoom_content.setText(item.getmRoom());
        viewHolder.tvPlace_content.setText(item.getmPlace());
        viewHolder.tvDate_content.setText(item.getmDate());
        viewHolder.tvTutor_content.setText(item.getmTutor());
        viewHolder.tvSubject_content.setText(item.getmSubject());

        viewHolder.tvIdSubject_content.setText(item.getmIdSubject());
        viewHolder.tvClass_content.setText(item.getmClass());
        viewHolder.tvSlot_content.setText(item.getmSlot());

        //..


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
        TextView contentRequestBtn;

    }
}
