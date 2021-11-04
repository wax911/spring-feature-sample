package com.example.demo.data.service;

import com.example.demo.data.mapper.contract.IMapper;
import com.example.demo.data.model.ContainerModel;
import com.example.demo.data.model.SimilarContainer;
import com.example.demo.data.service.contract.ISearchService;
import com.example.demo.data.source.RemoteSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService implements ISearchService {

    private final IMapper<SimilarContainer, ContainerModel> mapper;
    private final RemoteSource remoteSource;

    @Override
    @Cacheable("games")
    public ContainerModel findGames(Integer limit, String term) {
        Future<SimilarContainer> future = remoteSource.getSimilar(term, limit, 1, "game");
        SimilarContainer similarContainer = null;
        try {
            similarContainer = future.get();
        } catch (InterruptedException | ExecutionException e) {
            log.warn("Huston, we have a problem", e);
        }
        return mapper.convertFrom(similarContainer);
    }

    @Override
    @Cacheable("shows")
    public ContainerModel findShows(Integer limit, String term) {
        Future<SimilarContainer> future = remoteSource.getSimilar(term, limit, 1, "show");
        SimilarContainer similarContainer = null;
        try {
            similarContainer = future.get();
        } catch (InterruptedException | ExecutionException e) {
            log.warn("Huston, we have a problem", e);
        }
        return mapper.convertFrom(similarContainer);
    }

    @Override
    @Cacheable("movies")
    public ContainerModel findMovies(Integer limit, String term) {
        Future<SimilarContainer> future = remoteSource.getSimilar(term, limit, 1, "movie");
        SimilarContainer similarContainer = null;
        try {
            similarContainer = future.get();
        } catch (InterruptedException | ExecutionException e) {
            log.warn("Huston, we have a problem", e);
        }
        return mapper.convertFrom(similarContainer);
    }
}
