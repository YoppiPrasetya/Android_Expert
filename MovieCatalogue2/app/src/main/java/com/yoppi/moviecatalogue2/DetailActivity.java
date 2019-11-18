package com.yoppi.moviecatalogue2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    TextView txtTitle,txtYear,txtDescription;
    ImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtTitle = findViewById(R.id.txt_title);
        txtYear = findViewById(R.id.txt_year);
        txtDescription = findViewById(R.id.txt_description);
        imgPoster = findViewById(R.id.movie_poster);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        String title = movie.getTitle();
        String year = movie.getYear();
        String description = movie.getDescription();
        int poster = movie.getPhoto();

        txtTitle.setText(title);
        txtYear.setText(year);
        txtDescription.setText(description);
        imgPoster.setBackgroundResource(poster);
    }
}
