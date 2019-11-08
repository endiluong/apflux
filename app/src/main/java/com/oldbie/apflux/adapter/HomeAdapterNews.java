package com.oldbie.apflux.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oldbie.apflux.R;
import com.oldbie.apflux.model.News;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapterNews extends RecyclerView.Adapter<HomeAdapterNews.ViewHolder> {
    private static final String TAG = "NewsAdapter";
    private ArrayList<News> arrTiana;
    private Context context;
    private Date date;
    private SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    public HomeAdapterNews(ArrayList<News> arrTiana, Context context) {
        this.arrTiana = arrTiana;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vi;
        LayoutInflater inflater=LayoutInflater.from(context);
        vi=inflater.inflate(R.layout.item_news,parent,false);
        return new ViewHolder(vi);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pi) {

        News temp = arrTiana.get(pi);
        holder.tvTitle_news.setText(temp.getmTitle());
        holder.tvDate_news.setText(temp.getmDate());
        holder.tvPoster_news.setText(temp.getmPostMeta());
        holder.tvContent_news.setText(temp.getmContent());

        try {
            date = fmt.parse(temp.getmDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
            holder.tvDate_news.setText(fmtOut.format(date));
        switch (temp.getmNewType()) {
            case "1":
                holder.tvCate_news.setText("Thông tin học tập");
                break;
            case "2":
                holder.tvCate_news.setText("Thông tin hoạt động");
                break;
            case "3":
                holder.tvCate_news.setText("Thông tin học phí");
                break;
            default:
                holder.tvCate_news.setText("Nothing");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle_news;
        TextView tvCate_news;
        TextView tvDate_news;
        TextView tvPoster_news;
        TextView tvContent_news;

        ViewHolder(@NonNull View v) {
            super(v);
            tvTitle_news = v.findViewById(R.id.tvTitle_news);
            tvCate_news = v.findViewById(R.id.tvCate_news);
            tvDate_news = v.findViewById(R.id.tvDate_news);
            tvPoster_news= v.findViewById(R.id.tvPoster_news);
            tvContent_news = v.findViewById(R.id.tvContent_news);
        }
    }
}
