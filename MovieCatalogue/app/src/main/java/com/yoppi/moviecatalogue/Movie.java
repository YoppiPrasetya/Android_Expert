package com.yoppi.moviecatalogue;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String title, description, year;
    private int poster;

    protected Movie(Parcel in) {
        title = in.readString();
        description = in.readString();
        poster = in.readInt();
        year = in.readString();
    }

    public Movie(String title,String description, int poster,String year)
    {
        this.title = title;
        this.description = description;
        this.year = year;
        this.poster = poster;
    }

    public Movie(){

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(poster);
        dest.writeString(year);
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
