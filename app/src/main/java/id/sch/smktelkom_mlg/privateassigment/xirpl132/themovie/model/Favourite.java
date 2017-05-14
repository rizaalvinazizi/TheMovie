package id.sch.smktelkom_mlg.privateassigment.xirpl132.themovie.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by rizaalvin on 14/05/2017.
 */

public class Favourite extends SugarRecord implements Serializable {
    public String title;
    public String poster_path;
    public String popularity;
    public String overview;
    public String original_language;
    public String vote_average;
    public String release_date;

    public Favourite() {
    }

    public Favourite(String title, String poster_path, String popularity, String overview, String original_language, String vote_average, String release_date) {
        this.title = title;
        this.poster_path = poster_path;
        this.popularity = popularity;
        this.overview = overview;
        this.original_language = original_language;
        this.vote_average = vote_average;
        this.release_date = release_date;
    }
}

