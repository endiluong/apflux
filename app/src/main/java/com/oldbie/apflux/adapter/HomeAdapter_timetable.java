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

public class HomeAdapter_timetable extends RecyclerView.Adapter<HomeAdapter_timetable.ViewHolder> {

    private Context context;
    private ArrayList<TimeTable> arrTimeTable;
    private Date date;
    private SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    public HomeAdapter_timetable(Context context,ArrayList<TimeTable> arrTimeTable) {
        this.context = context;
        this.arrTimeTable = arrTimeTable;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vi;
        LayoutInflater inflater=LayoutInflater.from( context );
        vi=inflater.inflate( R.layout.item_timetable_home,parent,false );
        ViewHolder vh=new ViewHolder( vi );
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TimeTable temp = arrTimeTable.get( position );
        holder.tvSubject_head.setText( temp.getmSubject() );
        holder.tvSlot_head.setText( temp.getmSlot() );

        try {
            date = fmt.parse( temp.getmDate());
//            holder.tvDate_news.setText(fmtOut.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvDate_head.setText( fmtOut.format(date) );
        holder.tvRoom_head.setText( temp.getmRoom() );
        holder.tvPlace_head.setText( temp.getmPlace() );

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDate_head;
        private TextView tvSlot_head;
        private TextView tvSubject_head;
        private TextView tvPlace_head;
        private TextView tvRoom_head;

        public ViewHolder(@NonNull View vi) {
            super( vi );
            tvDate_head =vi.findViewById( R.id.tvDate_home );
            tvSlot_head =vi.findViewById( R.id.tvSlot_home );
            tvSubject_head =vi.findViewById( R.id.tvSubject_home );
            tvPlace_head =vi.findViewById( R.id.tvPlace_home );
            tvRoom_head =vi.findViewById( R.id.tvRoom_home );
        }
    }
}
