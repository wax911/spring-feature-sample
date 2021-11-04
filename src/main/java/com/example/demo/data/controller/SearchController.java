package com.example.demo.data.controller;

import com.example.demo.data.api.SearchApi;
import com.example.demo.data.model.ContainerModel;
import com.example.demo.data.service.SearchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class SearchController implements SearchApi {

    private final SearchService searchService;

    @Override
    public ResponseEntity<ContainerModel> findGames(Integer limit, String term) {
        log.debug("Requested games: {} for term: {}", limit, term);
        return ResponseEntity.ok(
            searchService.findGames(limit, term)
        );
    }

    @Override
    public ResponseEntity<ContainerModel> findMovies(Integer limit, String term) {
        log.debug("Requested movies: {} for term: {}", limit, term);
        return ResponseEntity.ok(
            searchService.findMovies(limit, term)
        );
    }

    @Override
    public ResponseEntity<ContainerModel> findShows(Integer limit, String term) {
        log.debug("Requested shows: {} for term: {}", limit, term);
        return ResponseEntity.ok(
            searchService.findShows(limit, term)
        );
    }
}
