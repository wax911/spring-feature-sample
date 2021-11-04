package com.example.demo.data.mapper.contract;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Mapper from one type to another
 */
public interface IMapper<FROM, TO> {
    /**
     * Maps objects from one type to another
     *
     * @param from Input type to map from
     *
     * @return The target type
     */
    @NonNull TO convertFrom(@Nullable FROM from);
}
