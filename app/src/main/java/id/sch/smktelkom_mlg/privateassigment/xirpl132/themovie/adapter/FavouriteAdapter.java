package id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.R;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.model.Favourite;

/**
 * Created by rizaalvin on 13/05/2017.
 */

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    Context context;
    ArrayList<Favourite> list;
    IArticleAdapter mIArticleAdapter;


    public FavouriteAdapter(Context context, ArrayList<Favourite> list) {
        this.list = list;
        this.context = context;
        mIArticleAdapter = (IArticleAdapter) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favrecycler, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Favourite fav = list.get(position);
        holder.tvName.setText(fav.title);
        holder.tvDeskripsi.setText(fav.overview);
        Glide.with(context).load("http://image.tmdb.org/t/p/w500" + fav.poster_path)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.ivArticle);
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public interface IArticleAdapter {
        void showDetail(String name, String path, String popularity, String overview, String orilang, String vote, String date);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivArticle;
        TextView tvName, tvDeskripsi;

        public ViewHolder(View itemView) {
            super(itemView);
            ivArticle = (ImageView) itemView.findViewById(R.id.imageViewPosterFav);
            tvName = (TextView) itemView.findViewById(R.id.textViewTitleFav);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewOverviewFav);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Favourite fav = list.get(getAdapterPosition());
                    mIArticleAdapter.showDetail(fav.title, fav.poster_path, fav.popularity, fav.overview, fav.original_language, fav.vote_average, fav.release_date);
                }
            });
        }
    }
}