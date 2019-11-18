package com.yoppi.moviecatalogue2;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private RecyclerView rvMovies;
    private ArrayList<Movie> listMovie = new ArrayList<>();

    private final String STATE_LIST = "state_list";


    public static MoviesFragment newInstance(int index){
        MoviesFragment fragment = new MoviesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        rvMovies = (RecyclerView) view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        int index = getArguments().getInt(ARG_SECTION_NUMBER);

        switch (index){
            case 1:
                if (savedInstanceState == null){
                    listMovie.addAll(getListMovie());
                    showRecyclerList();
                } else {

//                    ArrayList<Movie> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
//                    listMovie.addAll(stateList);
//                    showRecyclerList();
                    listMovie.addAll(getListMovie());
                    showRecyclerList();
                }
                break;

            case 2:
                if (savedInstanceState == null){
                    listMovie.addAll(getListTVShows());
                    showRecyclerList();
                } else {
//                    ArrayList<Movie> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
//                    listMovie.addAll(stateList);
//                    showRecyclerList();
                    listMovie.addAll(getListTVShows());
                    showRecyclerList();
                }
                break;
        }
    }

    public ArrayList<Movie> getListMovie(){
        String[] dataTitle = getResources().getStringArray(R.array.data_title_movies);
        String[] dataYear = getResources().getStringArray(R.array.data_year_movies);
        String[] dataDescription = getResources().getStringArray(R.array.data_description_movies);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_poster_movies);

        ArrayList<Movie> listMovie = new ArrayList<>();
        for (int i = 0; i < dataTitle.length; i++){
            Movie movie = new Movie();
            movie.setTitle(dataTitle[i]);
            movie.setYear(dataYear[i]);
            movie.setDescription(dataDescription[i]);
            movie.setPhoto(dataPhoto.getResourceId(i,-1));

            listMovie.add(movie);
        }
        return listMovie;
    }

    public ArrayList<Movie> getListTVShows(){
        String[] dataTitle = getResources().getStringArray(R.array.data_title_tvshows);
        String[] dataYear = getResources().getStringArray(R.array.data_year_tvshows);
        String[] dataDescription = getResources().getStringArray(R.array.data_description_tvshows);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_poster_tvshows);

        ArrayList<Movie> listMovie = new ArrayList<>();
        for (int i = 0; i < dataTitle.length; i++){
            Movie movie = new Movie();
            movie.setTitle(dataTitle[i]);
            movie.setYear(dataYear[i]);
            movie.setDescription(dataDescription[i]);
            movie.setPhoto(dataPhoto.getResourceId(i,-1));

            listMovie.add(movie);
        }
        return listMovie;
    }

    private void showRecyclerList(){
        rvMovies.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(listMovie);
        rvMovies.setAdapter(listMovieAdapter);

        listMovieAdapter.setOnItemClickCallback(new ListMovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                showSelectedHero(data);
            }
        });
    }

    private void showSelectedHero(Movie movie){
//        Toast.makeText(this, "Kamu Meimilih "+movie.getTitle(), Toast.LENGTH_SHORT).show();

        Movie m = new Movie();
        m.setTitle(movie.getTitle());
        m.setYear(movie.getYear());
        m.setDescription(movie.getDescription());
        m.setPhoto(movie.getPhoto());

        Intent mIntent = new Intent(getActivity(), DetailActivity.class);
        mIntent.putExtra(DetailActivity.EXTRA_MOVIE, m);
        startActivity(mIntent);
    }


    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_LIST, listMovie);

    }



}
