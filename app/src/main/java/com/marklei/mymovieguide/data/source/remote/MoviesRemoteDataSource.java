package com.marklei.mymovieguide.data.source.remote;

import androidx.annotation.NonNull;

import com.marklei.mymovieguide.data.Movie;
import com.marklei.mymovieguide.data.Review;
import com.marklei.mymovieguide.data.Video;
import com.marklei.mymovieguide.data.source.MoviesDataSource;
import com.marklei.mymovieguide.data.source.remote.network.TmdbWebService;
import com.marklei.mymovieguide.data.source.remote.network.wrapper.MoviesWrapper;
import com.marklei.mymovieguide.data.source.remote.network.wrapper.ReviewsWrapper;
import com.marklei.mymovieguide.data.source.remote.network.wrapper.VideoWrapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class MoviesRemoteDataSource implements MoviesDataSource {

    private final TmdbWebService tmdbWebService;

    @Inject
    MoviesRemoteDataSource(TmdbWebService tmdbWebService) {
        this.tmdbWebService = tmdbWebService;
    }

    @Override
    public Flowable<List<Movie>> fetchPopularMovies(int page) {
        return tmdbWebService.popularMovies(page).map(MoviesWrapper::getMovieList);
    }

    @Override
    public Flowable<List<Movie>> fetchHighestRatedMovies(int page) {
        return tmdbWebService.highestRatedMovies(page).map(MoviesWrapper::getMovieList);
    }

    @Override
    public Flowable<List<Movie>> fetchFavoritesMovies() {
        return null;
    }

    @Override
    public Flowable<List<Video>> getTrailers(String id) {
        return tmdbWebService.trailers(id).map(VideoWrapper::getVideos);
    }

    @Override
    public Flowable<List<Review>> getReviews(String id) {
        return tmdbWebService.reviews(id).map(ReviewsWrapper::getReviews);
    }

    @Override
    public Flowable<Integer> getFavorite(String id) {
        return null;
    }

    @Override
    public void saveMovie(@NonNull Movie movie) {
        // Do nothing
    }

    @Override
    public void updateMovie(@NonNull Movie movie) {
        // Do nothing
    }

    @Override
    public void refreshMovies() {
        // Do nothing
    }
}
