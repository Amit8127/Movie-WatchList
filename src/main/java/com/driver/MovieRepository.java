package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieRepository {
    private Map<String, Movie> movies = new HashMap<>();
    private Map<String, Director> directors = new HashMap<>();
    private Map<String, List<String>> movieDirectorPairs = new HashMap<>();


    public void add(Movie movie) {
        movies.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        directors.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movie, String director) {
        List<String> movieList = new ArrayList<>();
        if(movieDirectorPairs.containsKey(director)){
            movieList = movieDirectorPairs.get(director);
        }
        if(!movieList.contains(movie)){
            movieList.add(movie);
        }
        movieDirectorPairs.put(director, movieList);
    }

    public Movie getMovieByName(String name) {
        return movies.get(name);
    }

    public Director getDirectorByName(String name) {
        return directors.get(name);
    }

    public List<String> getMoviesByDirectorName(String director) {
        List<String> movieList = new ArrayList<>();
        if(movieDirectorPairs.containsKey(director))
            movieList = movieDirectorPairs.get(director);
        return movieList;
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movies.keySet());
    }

    public void deleteDirectorByName(String director) {
        List<String> pairlist = new ArrayList<>();
        if (movieDirectorPairs.containsKey(director)) {
            pairlist = movieDirectorPairs.get(director);
            for (String st : pairlist) {
                movies.remove(st);
            }
            movieDirectorPairs.remove(director);
        }
        directors.remove(director);
    }

    public void deleteAllDirectors() {
        for(String director : movieDirectorPairs.keySet()) {
            List<String> pairList = movieDirectorPairs.get(director);
            for(String st : pairList) {
                if(movies.containsKey(st)) {
                    movies.remove(st);
                }
            }
        }
        movies.clear();
        movieDirectorPairs.clear();
    }
}
