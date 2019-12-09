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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TimeTableAdapter extends ArrayAdapter<TimeTable> {
    private Date date;
    private SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();

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
            viewHolder.tvDate_head =cell.findViewById(R.id.tvDate_head);
            viewHolder.tvWeekDay_head =cell.findViewById(R.id.tvWeekDay_head);
            viewHolder.tvSlot_head =cell.findViewById(R.id.tvSlot_head);
            viewHolder.tvSubject_head =cell.findViewById(R.id.tvSubject_head);
            viewHolder.tvPlace_head =cell.findViewById(R.id.tvPlace_head);
            viewHolder.tvRoom_head =cell.findViewById(R.id.tvRoom_head);

            //.. Content ..//
            viewHolder.tvRoom_content =cell.findViewById(R.id.tvRoom_content);
            viewHolder.tvPlace_content =cell.findViewById(R.id.tvPlace_content);
            viewHolder.tvWeekDay_content =cell.findViewById(R.id.tvWeekDay_content);
            viewHolder.tvDate_content =cell.findViewById(R.id.tvDate_content);
            viewHolder.tvTutor_content =cell.findViewById(R.id.tvTutor_content);
            viewHolder.tvSubject_content =cell.findViewById(R.id.tvSubject_content);
            viewHolder.tvIdSubject_content =cell.findViewById(R.id.tvIdSubject_content);
            viewHolder.tvClass_content =cell.findViewById(R.id.tvClass_content);
            viewHolder.tvSlot_content =cell.findViewById(R.id.tvSlot_content);

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

        try {
            date = fmt.parse(item.getmDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //.. bind data from selected element to view through view holder
        //.. head
        if (item.getmWeekDay()==2){
            viewHolder.tvWeekDay_head.setText("Thứ hai");
        }else if (item.getmWeekDay()==3){
            viewHolder.tvWeekDay_head.setText("Thứ ba");
        }else if (item.getmWeekDay()==4){
            viewHolder.tvWeekDay_head.setText("Thứ tư");
        }else if (item.getmWeekDay()==5){
            viewHolder.tvWeekDay_head.setText("Thứ năm ");
        }else if (item.getmWeekDay()==6){
            viewHolder.tvWeekDay_head.setText("Thứ sáu");
        }else if (item.getmWeekDay()==7){
            viewHolder.tvWeekDay_head.setText("Thứ bảy");
        }else {
            viewHolder.tvWeekDay_head.setText("Chủ nhật");
        }
        viewHolder.tvDate_head.setText("("+fmtOut.format(date)+")");
        viewHolder.tvSlot_head.setText(item.getmSlot());
        viewHolder.tvSubject_head.setText(item.getmSubject());
        viewHolder.tvPlace_head.setText(item.getmPlace());
        viewHolder.tvRoom_head.setText(item.getmRoom());
        //.. content
        viewHolder.tvRoom_content.setText(item.getmRoom());
        viewHolder.tvPlace_content.setText(item.getmPlace());

        if (item.getmWeekDay()==2){
            viewHolder.tvWeekDay_content.setText("Thứ hai");
        }else if (item.getmWeekDay()==3){
            viewHolder.tvWeekDay_content.setText("Thứ ba");
        }else if (item.getmWeekDay()==4){
            viewHolder.tvWeekDay_content.setText("Thứ tư");
        }else if (item.getmWeekDay()==5){
            viewHolder.tvWeekDay_content.setText("Thứ năm ");
        }else if (item.getmWeekDay()==6){
            viewHolder.tvWeekDay_content.setText("Thứ sáu");
        }else if (item.getmWeekDay()==7){
            viewHolder.tvWeekDay_content.setText("Thứ bảy");
        }else {
            viewHolder.tvWeekDay_content.setText("Chủ nhật");
        }

        viewHolder.tvDate_content.setText("("+fmtOut.format(date)+")");
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

    // View lookup cache
    private static class ViewHolder {
        //.. Head ..//
        TextView tvDate_head;
        TextView tvWeekDay_head;
        TextView tvSlot_head;
        TextView tvSubject_head;
        TextView tvPlace_head;
        TextView tvRoom_head;
        //.. Content ..//
        TextView tvRoom_content;
        TextView tvPlace_content;
        TextView tvDate_content;
        TextView tvWeekDay_content;
        TextView tvTutor_content;
        TextView tvSubject_content;
        TextView tvIdSubject_content;
        TextView tvClass_content;
        TextView tvSlot_content;
    }
}
