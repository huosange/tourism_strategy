package com.tourism.strategy.tourism_strategy.net;

import com.tourism.strategy.tourism_strategy.model.Category;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface MyApi {

    String BASE_URL="http://chanyouji.com/";

    @GET("api/destinations.json")
    Observable<List<Category>> getAreas();
}
