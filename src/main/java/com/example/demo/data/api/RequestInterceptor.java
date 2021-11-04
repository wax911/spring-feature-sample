package com.example.demo.data.api;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Request interceptor for adding headers to our requests
 */
public final class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.addHeader("accept", "application/json");
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
