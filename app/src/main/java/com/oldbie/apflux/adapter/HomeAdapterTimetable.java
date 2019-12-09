package com.oldbie.apflux.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oldbie.apflux.R;
import com.oldbie.apflux.model.TimeTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapterTimetable extends RecyclerView.Adapter<HomeAdapterTimetable.ViewHolder> {
    private Context context;
    private ArrayList<TimeTable> arrTimeTable;
    private Date date;
    private SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    public HomeAdapterTimetable(Context context, ArrayList<TimeTable> arrTimeTable) {
        this.context = context;
        this.arrTimeTable = arrTimeTable;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vi;
        LayoutInflater inflater = LayoutInflater.from( context );
        vi = inflater.inflate(R.layout.item_timetable_home,parent,false);
        return new ViewHolder(vi);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimeTable temp = arrTimeTable.get( position);
        holder.tvSubject_head.setText(temp.getmSubject());
        holder.tvSlot_head.setText(temp.getmSlot());
        try {
            date = fmt.parse( temp.getmDate());

        } catch (ParseException e){
            e.printStackTrace();
        }
        if (temp.getmWeekDay()==2){
            holder.tvWeekDay_head.setText("Thứ 2");
        }else if (temp.getmWeekDay()==3){
            holder.tvWeekDay_head.setText("Thứ 3");
        }else if (temp.getmWeekDay()==4){
            holder.tvWeekDay_head.setText("Thứ 4");
        }else if (temp.getmWeekDay()==5){
            holder.tvWeekDay_head.setText("Thứ 5");
        }else if (temp.getmWeekDay()==6){
            holder.tvWeekDay_head.setText("Thứ 6");
        }else if (temp.getmWeekDay()==7){
            holder.tvWeekDay_head.setText("Thứ 7");
        }else {
            holder.tvWeekDay_head.setText("Chủ nhật");
        }
        holder.tvDate_head.setText("("+fmtOut.format(date)+")");
        holder.tvRoom_head.setText(temp.getmRoom());
        holder.tvPlace_head.setText(temp.getmPlace());
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDate_head;
        private TextView tvSlot_head;
        private TextView tvSubject_head;
        private TextView tvPlace_head;
        private TextView tvRoom_head;
        private TextView tvWeekDay_head;

        ViewHolder(@NonNull View vi) {
            super(vi);
            tvDate_head = vi.findViewById(R.id.tvDate_home);
            tvSlot_head = vi.findViewById(R.id.tvSlot_home);
            tvSubject_head = vi.findViewById(R.id.tvSubject_home);
            tvPlace_head = vi.findViewById(R.id.tvPlace_home);
            tvRoom_head = vi.findViewById(R.id.tvRoom_home);
            tvWeekDay_head = vi.findViewById(R.id.tvWeekDay_home);
        }
    }
}
