package id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.adapter.FavouriteAdapter;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.adapter.NowPlayingAdapter;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.adapter.PopularAdapter;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.adapter.TopRatedAdapter;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.adapter.UpcomingAdapter;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.fragment.FavouriteFragment;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.fragment.NowPlayingFragment;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.fragment.PopularFragment;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.fragment.TopRatedFragment;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.fragment.UpcomingFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NowPlayingAdapter.IArticleAdapter, PopularAdapter.IArticleAdapter, TopRatedAdapter.IArticleAdapter, UpcomingAdapter.IArticleAdapter, FavouriteAdapter.IArticleAdapter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        changePage(R.id.nav_now_playing);
        navigationView.setCheckedItem(R.id.nav_now_playing);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatemen

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        changePage(id);
        return true;
    }

    private void changePage(int id) {
        Fragment fragment = null;

        if (id == R.id.nav_now_playing) {
            fragment = new NowPlayingFragment();
        } else if (id == R.id.nav_popular) {
            fragment = new PopularFragment();
        } else if (id == R.id.nav_top_rated) {
            fragment = new TopRatedFragment();
        } else if (id == R.id.nav_upcoming) {
            fragment = new UpcomingFragment();
        } else if (id == R.id.nav_fav) {
            fragment = new FavouriteFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitNow();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public void showDetail(String name, String path, String popularity, String overview, String orilang, String vote, String date) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("path", path);
        intent.putExtra("popularity", popularity);
        intent.putExtra("overview", overview);
        intent.putExtra("orilang", orilang);
        intent.putExtra("vote", vote);
        intent.putExtra("date", date);

        startActivity(intent);
    }
}
