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
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.model.Popular;

/**
 * Created by rizaalvin on 13/05/2017.
 */

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    ArrayList<Popular> list;
    IArticleAdapter mIArticleAdapter;
    Context context;

    public PopularAdapter(Context context, ArrayList<Popular> list) {
        this.list = list;
        this.context = context;
        mIArticleAdapter = (IArticleAdapter) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listrecycler, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Popular popular = list.get(position);
        holder.tvName.setText(popular.title);
        holder.tvDeskripsi.setText(popular.overview);
        Glide.with(context).load("http://image.tmdb.org/t/p/w500" + popular.poster_path)
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
            ivArticle = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            tvName = (TextView) itemView.findViewById(R.id.textViewName);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewDeskripsi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Popular popular = list.get(getAdapterPosition());
                    mIArticleAdapter.showDetail(popular.title, popular.poster_path, popular.popularity, popular.overview, popular.original_language, popular.vote_average, popular.release_date);
                }
            });
        }
    }
}