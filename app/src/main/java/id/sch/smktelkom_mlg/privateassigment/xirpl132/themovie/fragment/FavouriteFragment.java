package id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.R;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.adapter.FavouriteAdapter;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.model.Favourite;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {

    ArrayList<Favourite> mList = new ArrayList<>();
    FavouriteAdapter mFavouriteAdapter;

    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.favourite);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mFavouriteAdapter = new FavouriteAdapter(this.getActivity(), mList);
        recyclerView.setAdapter(mFavouriteAdapter);

        getActivity().setTitle("Favourite");
        refreshData(null);
    }

    private void refreshData(String query) {
        mList.clear();

        if (query == null || query.isEmpty())
            mList.addAll(Favourite.listAll(Favourite.class));

        mFavouriteAdapter.notifyDataSetChanged();
    }


}
