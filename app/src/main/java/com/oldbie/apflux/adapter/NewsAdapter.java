package com.oldbie.apflux.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oldbie.apflux.R;
import com.oldbie.apflux.model.News;
import java.util.ArrayList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private static final String TAG = "NewsAdapter";
    private ArrayList<News> arrTiana;
    private Context context;
    private Date date;
    private SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    public NewsAdapter(ArrayList<News> arrTiana, Context context) {
        this.arrTiana = arrTiana;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vi;
        LayoutInflater inflater=LayoutInflater.from( context );
        vi=inflater.inflate( R.layout.item_news,parent,false );
        ViewHolder vh=new ViewHolder( vi );
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pi) {


        News temp=arrTiana.get( pi );
        holder.tvTitle_news.setText(temp.getmTitle());
        holder.tvDate_news.setText(temp.getmDate());
        holder.tvPoster_news.setText(temp.getmPostMeta());
        holder.tvContent_news.setText(temp.getmContent());

        try {
            date = fmt.parse( temp.getmDate());
//            holder.tvDate_news.setText(fmtOut.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
            holder.tvDate_news.setText(fmtOut.format(date));
        if(temp.getmNewType().equals("1")){
            holder.tvCate_news.setText("Thông tin học tập");
        }else if(temp.getmNewType().equals("2")){
            holder.tvCate_news.setText( "Thông tin hoạt động" );
        }else if(temp.getmNewType().equals( "3" )){
            holder.tvCate_news.setText( "Thông tin học phí" );
        }else {
            holder.tvCate_news.setText( "Nothing" );
        }
    }

    @Override
    public int getItemCount() {
        return arrTiana.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle_news;
        TextView tvCate_news;
        TextView tvDate_news;
        TextView tvPoster_news;
        TextView tvContent_news;

        public ViewHolder(@NonNull View v) {

            super( v );
            tvTitle_news = v.findViewById( R.id.tvTitle_news );
            tvCate_news = v.findViewById( R.id.tvCate_news );
            tvDate_news = v.findViewById( R.id.tvDate_news );
            tvPoster_news= v.findViewById( R.id.tvPoster_news );
            tvContent_news = v.findViewById( R.id.tvContent_news );
        }
    }
}
