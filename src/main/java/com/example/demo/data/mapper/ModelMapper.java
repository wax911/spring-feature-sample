package com.example.demo.data.mapper;

import com.example.demo.data.mapper.contract.IMapper;
import com.example.demo.data.model.ContainerModel;
import com.example.demo.data.model.Item;
import com.example.demo.data.model.ResultModel;
import com.example.demo.data.model.SimilarContainer;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public class ModelMapper implements IMapper<SimilarContainer, ContainerModel> {

    private ResultModel createResultModelFrom(Item item) {
        ResultModel resultModel = new ResultModel();
        resultModel.setId(item.getName().hashCode());
        resultModel.setTitle(item.getName());
        resultModel.setCategory(item.getType());
        resultModel.setWikipedia(item.getWikipediaUrl());
        resultModel.setYoutube(item.getYoutubeId());
        return resultModel;
    }

    /**
     * Maps objects from one type to another
     *
     * @param from Input type to map from
     *
     * @return The target type
     */
    @Override @NonNull
    public ContainerModel convertFrom(@Nullable SimilarContainer from) {
        ContainerModel containerModel = new ContainerModel();
        if (from != null) {
            List<ResultModel> results = from.getSimilar()
                    .getResults().stream()
                    .map(this::createResultModelFrom)
                    .collect(Collectors.toList());
            containerModel.data(results);
        }
        return containerModel;
    }
}
