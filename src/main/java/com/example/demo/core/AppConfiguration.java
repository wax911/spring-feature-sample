package com.example.demo.core;

import com.example.demo.core.settings.AppSettings;
import com.example.demo.data.api.RequestInterceptor;
import com.example.demo.data.mapper.ModelMapper;
import com.example.demo.data.mapper.contract.IMapper;
import com.example.demo.data.model.ContainerModel;
import com.example.demo.data.model.SimilarContainer;
import com.example.demo.data.source.RemoteSource;
import lombok.AllArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Configuration
@AllArgsConstructor
public class AppConfiguration {

    private final AppSettings settings;
    private final Environment environment;

    /**
     * Request interceptor
     */
    @Bean("requestInterceptor")
    public Interceptor requestInterceptor() {
        return new RequestInterceptor();
    }

    @Bean
    public IMapper<SimilarContainer, ContainerModel> defaultMapper() {
        return new ModelMapper();
    }

    /**
     * Default OkHttp client
     */
    @Bean("okHttpClient")
    public OkHttpClient okHttpClient(@Qualifier("requestInterceptor") Interceptor interceptor) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true);
        return clientBuilder.build();
    }

    /**
     * Produces a retrofit service interface
     *
     * @param okHttpClient Configured client
     */
    @Bean
    public RemoteSource remoteSource(@Qualifier("okHttpClient") OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(settings.getClient())
                .client(okHttpClient)
                .addConverterFactory(
                    GsonConverterFactory.create()
                ).build();

        // We could return retrofit and declare other di modules to produce service interfaces as needs
        return retrofit.create(RemoteSource.class);
    }
}
