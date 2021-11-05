package com.example.demo.data.mapper;

import com.example.demo.data.model.Item;
import com.example.demo.data.model.ResultModel;
import com.example.demo.data.model.Similar;
import com.example.demo.data.model.SimilarContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ModelMapperTest {

    private SimilarContainer similarContainer;

    @BeforeEach
    void setUp() {
        Similar similar = mock(Similar.class);
        similarContainer = mock(SimilarContainer.class);
        when(similar.getResults()).thenReturn(
            List.of(
                new Item(
                        "Nirvana",
                        "music",
                        "Nirvana was an American rock band formed in Aberdeen...",
                        "http://en.wikipedia.org/wiki/Ted_Ed_Fred",
                        "hTWKbfoikeg"
                ),
                new Item(
                        "Foo Fighters",
                        "music",
                        "Foo Fighters is an American rock...",
                        "http://en.wikipedia.org/wiki/Foo_Fighters_(band)",
                        "O6cVpDO40lM"
                )
            )
        );
        when(similarContainer.getSimilar()).thenReturn(similar);
    }

    @Test
    void convertFrom() {
        ModelMapper mapper = new ModelMapper();

        List<ResultModel> expected = List.of(
            new ResultModel()
                    .id(-674202603)
                    .title("Nirvana")
                    .category("music")
                    .wikipedia("http://en.wikipedia.org/wiki/Ted_Ed_Fred")
                    .youtube("hTWKbfoikeg"),
            new ResultModel()
                    .id(-304729680)
                    .title("Foo Fighters")
                    .category("music")
                    .wikipedia("http://en.wikipedia.org/wiki/Foo_Fighters_(band)")
                    .youtube("O6cVpDO40lM")
        );
        List<ResultModel> actual = mapper.convertFrom(similarContainer).getData();

        assertThat(actual).isEqualTo(expected);
        verify(similarContainer);
    }
}