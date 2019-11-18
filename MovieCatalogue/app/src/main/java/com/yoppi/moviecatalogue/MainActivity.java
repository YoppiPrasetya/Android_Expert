package com.yoppi.moviecatalogue;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] dataTitle;
    private int[] dataYear;
    private String[] dataDescription;
    private TypedArray dataPoster;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies = new ArrayList<>();

    private String title, description, year;
    private int poster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        ListView listView = findViewById(R.id.lv_list);
        listView.setAdapter(adapter);

        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                //Toast.makeText(MainActivity.this, movies.get(i).getTitle(),Toast.LENGTH_SHORT).show();
                Movie movie = new Movie();
                movie.setTitle(movies.get(i).getTitle());
                movie.setYear(movies.get(i).getYear());
                movie.setDescription(movies.get(i).getDescription());
                movie.setPoster(movies.get(i).getPoster());

                Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
                startActivity(intent);
            }
        });
    }


    private void addItem(){
        movies.addAll(MovieData.getListData());
        adapter.setMovies(movies);
    }

//    private void prepare(){
//        dataTitle = getResources().getStringArray(R.array.data_title);
//        dataYear = getResources().getIntArray(R.array.data_year);
//        dataDescription = getResources().getStringArray(R.array.data_description);
//        dataPoster = getResources().obtainTypedArray(R.array.data_poster);
//    }
}
