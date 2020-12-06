package labs.android2020.readingapp2020.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

import labs.android2020.readingapp2020.Interface.Item;
import labs.android2020.readingapp2020.R;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClicked(int position);
        void onItemLongClickListener(int position);
    }

    private Context context;
    private ArrayList<Item> data;
    private OnItemClickListener listener;

    public AdapterList(Context context,ArrayList<Item> data, AdapterList.OnItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    /**
     * Creates a ViewHolder for the RecyclerView to represent Items.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        AdapterList.ViewHolder holder = new ViewHolder(view, listener);
        return holder;
    }

    /**
     * Updates the information displayed on the ViewHolder according to the given position.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        // Update the subViews within the holder with information from the data source
        holder.tv.setText(data.get(position).getText());
        holder.tv.setCompoundDrawablesWithIntrinsicBounds(
                context.getResources().getDrawable(data.get(position).getImage(),null),
                null, null, null);
    }

    public void removeItem(int position){
        data.remove(position);
        notifyDataSetChanged();
    }

    /**
     * Returns the number of elements in the data source.
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View v;
        // Hold a reference to a TextView to later change its text
        TextView tv;
        public ViewHolder(View view, final AdapterList.OnItemClickListener listener) {
            super(view);
            v = view;
            tv = view.findViewById(R.id.tvItem);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(getAdapterPosition());
                }

            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClickListener(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
