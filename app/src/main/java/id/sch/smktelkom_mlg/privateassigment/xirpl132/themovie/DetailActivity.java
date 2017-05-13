package id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.adapter.NowPlayingAdapter;

public class DetailActivity extends AppCompatActivity {

    String getName, getOverview, getOriLang, getPopularity, getPath, getRelease, getVote;
    TextView tvOverview, tvOriLang, tvPopularity, tvRelease, tvVote, tvName;
    ImageView ivPoster;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getPath = getIntent().getStringExtra("path");
        getName = getIntent().getStringExtra("name");
        getOverview = getIntent().getStringExtra("overview");
        getOriLang = getIntent().getStringExtra("orilang");
        getPopularity = getIntent().getStringExtra("popularity");
        getRelease = getIntent().getStringExtra("date");
        getVote = getIntent().getStringExtra("vote");

        tvOverview = (TextView) findViewById(R.id.overviewDetail);
        tvOriLang = (TextView) findViewById(R.id.originalLanguageDetail);
        tvPopularity = (TextView) findViewById(R.id.popularityDetail);
        tvVote = (TextView) findViewById(R.id.textViewVoteAverageDetail);
        tvName = (TextView) findViewById(R.id.textViewNameDetail);
        tvRelease = (TextView) findViewById(R.id.textViewReleaseDateDetail);
        ivPoster = (ImageView) findViewById(R.id.imageViewPosterDetaill);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setTitle(getName);
        setText();
    }

    private void setText() {

        tvOverview.setText(getOverview);
        tvPopularity.setText(getPopularity);
        tvOriLang.setText(getOriLang);
        tvName.setText(getName);
        tvVote.setText(getVote);
        tvRelease.setText(getRelease);

        Glide.with(NowPlayingAdapter.context).load("http://image.tmdb.org/t/p/w500" + getPath)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(ivPoster);
    }

}
