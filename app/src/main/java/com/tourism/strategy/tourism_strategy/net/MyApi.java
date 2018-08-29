package com.tourism.strategy.tourism_strategy.net;

import com.tourism.strategy.tourism_strategy.model.Album;
import com.tourism.strategy.tourism_strategy.model.Attraction;
import com.tourism.strategy.tourism_strategy.model.AttractionDetail;
import com.tourism.strategy.tourism_strategy.model.AttractionTrips;
import com.tourism.strategy.tourism_strategy.model.Category;
import com.tourism.strategy.tourism_strategy.model.Zone;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MyApi {

    String BASE_URL = "http://chanyouji.com/";

    @GET("api/destinations.json")
    Observable<List<Category>> getAreas();

    @GET("api/destinations/{cid}.json ")
    Observable<List<Zone>> getCountry(@Path("cid") int cid);

    @GET("api/destinations/attractions/{cid}.json")
    Observable<List<Attraction>> getAttractions(@Path("cid") int cid, @Query("page") int page);

    @GET("api/attractions/{attractionId}.json ")
    Observable<AttractionDetail> getAttraction(@Path("attractionId") int attractionId);

    @GET("api/attractions/photos/{attractionId}.json")
    Observable<List<Album>> getAlbum(@Path("attractionId") int attractionId, @Query("page") int page);

    @GET("api/attractions/{attractionId}.json?attraction_trips=true")
    Observable<AttractionTrips> getTourism(@Path("attractionId") int attractionId, @Query("page") int page);
}
