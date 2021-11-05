package com.example.demo.data.api;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.lang.NonNull;

import java.io.IOException;

/**
 * Request interceptor for adding headers to our requests
 */
public final class RequestInterceptor implements Interceptor {
    @Override @NonNull
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.addHeader("accept", "application/json");
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
