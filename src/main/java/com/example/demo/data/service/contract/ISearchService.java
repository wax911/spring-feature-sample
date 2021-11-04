package com.example.demo.data.service.contract;

import com.example.demo.data.model.ContainerModel;
import org.springframework.lang.Nullable;

public interface ISearchService {
    @Nullable ContainerModel findGames(Integer limit, String term);
    @Nullable ContainerModel findShows(Integer limit, String term);
    @Nullable ContainerModel findMovies(Integer limit, String term);
}
