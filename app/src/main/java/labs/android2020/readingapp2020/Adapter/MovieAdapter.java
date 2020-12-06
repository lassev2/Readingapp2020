package labs.android2020.readingapp2020.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import labs.android2020.readingapp2020.Database.Movie;
import labs.android2020.readingapp2020.R;


public class MovieAdapter extends ListAdapter<Movie, MovieAdapter.MovieHolder> {
    private OnItemClickListener listener;

    public MovieAdapter() {
        super(DIFF_CALLBACK);
    }
    private static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getProducer().equals(newItem.getProducer()) &&
                    oldItem.getYear().equals(newItem.getYear()) &&
                    oldItem.getCountry().equals(newItem.getCountry());

        }
    };

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie currentCompany = getItem(position);
        holder.textViewName.setText(currentCompany.getName());
        holder.textViewAddress.setText(currentCompany.getProducer());
        holder.textViewCity.setText(currentCompany.getYear());
        holder.textViewEmail.setText(currentCompany.getCountry());

    }

    public Movie getMovie(int position) {
        return getItem(position);
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewAddress;
        private TextView textViewCity;
        private TextView textViewEmail;


        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewAddress = itemView.findViewById(R.id.text_view_producer);
            textViewCity = itemView.findViewById(R.id.text_view_year);
            textViewEmail = itemView.findViewById(R.id.text_view_country);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
