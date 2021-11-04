package com.example.demo.data.source;

import com.example.demo.data.model.SimilarContainer;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.concurrent.CompletableFuture;

public interface RemoteSource {

    /**
     * Get similar listings of various types
     *
     * @param query the search query; consists of a series (at least one) of bands, movies,
     *              TV shows, podcasts, books, authors and/or games, separated by commas
     * @param limit maximum number of recommendations to retrieve. Default is 20.
     * @param info when set to 1, additional information is provided for the recommended items,
     *             like a description and a related YouTube clip (when available)
     * @param type query type, specifies the desired type of results. It can be one of the following:
     *             music, movies, shows, podcasts, books, authors, games. If not specified,
     *             the results can have mixed types.
     */
    @GET("similar")
    CompletableFuture<SimilarContainer> getSimilar(
        @Query("q") String query,
        @Query("limit") int limit,
        @Query("info") int info,
        @Query("type") String type
    );
}
