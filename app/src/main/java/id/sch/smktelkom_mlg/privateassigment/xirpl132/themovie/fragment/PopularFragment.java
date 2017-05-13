package id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.R;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.adapter.PopularAdapter;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.model.Popular;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.model.PopularResponse;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.service.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    ArrayList<Popular> mList = new ArrayList<>();
    PopularAdapter mPopularAdapter;

    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.popular);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mPopularAdapter = new PopularAdapter(this.getActivity(), mList);
        recyclerView.setAdapter(mPopularAdapter);

        getActivity().setTitle("Popular");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        downloadDataSources();
    }

    private void downloadDataSources() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=25b1b63ecfba8aed739100ca353e9344&language=en-US&page=1";

        GsonGetRequest<PopularResponse> myRequest = new GsonGetRequest<PopularResponse>
                (url, PopularResponse.class, null, new Response.Listener<PopularResponse>() {

                    @Override
                    public void onResponse(PopularResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));

                        mList.addAll(response.results);
                        mPopularAdapter.notifyDataSetChanged();

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this.getActivity()).addToRequestQueue(myRequest);
    }

}
