package labs.android2020.readingapp2020.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import labs.android2020.readingapp2020.Interface.bookItem;
import labs.android2020.readingapp2020.R;
import labs.android2020.readingapp2020.Services.GoogleBookService;

public class AdapterDiscover extends RecyclerView.Adapter<AdapterDiscover.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    private Context context;
    private ArrayList<bookItem> data;
    private OnItemClickListener listener;


    public AdapterDiscover(Context context, ArrayList<bookItem> data, OnItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_bookitem_discover, parent, false);
        ViewHolder holder = new ViewHolder(view, listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(data.get(position).getText());
        data.get(position).setImage(holder.im);
        GoogleBookService googleBookService = new GoogleBookService();

        if(data.get(position).getUrl() != null) {
            googleBookService.setBookThumbnailAdapter(data.get(position).getUrl(), holder.im);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View v;
        TextView tv;
        ImageView im;
        public ViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            v = view;
            im = view.findViewById(R.id.ivbooklist);
            tv = view.findViewById(R.id.tvBooklist);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }
}