package com.tourism.strategy.tourism_strategy.net;

import com.tourism.strategy.tourism_strategy.model.Album;
import com.tourism.strategy.tourism_strategy.model.Attraction;
import com.tourism.strategy.tourism_strategy.model.AttractionDetail;
import com.tourism.strategy.tourism_strategy.model.AttractionTrips;
import com.tourism.strategy.tourism_strategy.model.Category;
import com.tourism.strategy.tourism_strategy.model.HomeTrip;
import com.tourism.strategy.tourism_strategy.model.HomeTripDetail;
import com.tourism.strategy.tourism_strategy.model.Plan;
import com.tourism.strategy.tourism_strategy.model.PlanDetail;
import com.tourism.strategy.tourism_strategy.model.Weather;
import com.tourism.strategy.tourism_strategy.model.Wiki;
import com.tourism.strategy.tourism_strategy.model.WikiDestinations;
import com.tourism.strategy.tourism_strategy.model.Zone;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
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

    @GET("api/destinations/plans/{cid}.json")
    Observable<List<Plan>> getPlans(@Path("cid") int cid, @Query("page") int page);

    @GET("api/plans/{planId}.json")
    Observable<PlanDetail> getPlanDetail(@Path("planId") int planId);

    @GET("api/wiki/destinations/{id}.json")
    Observable<List<Wiki>> getWiki(@Path("id") int id);

    @GET("api/trips/featured.json")
    Observable<List<HomeTrip>> getHomeTrip(@Query("page") int page);

    /**
     * 目的地列表
     * @return
     */
    @GET("api/wiki/destinations.json")
    Observable<List<WikiDestinations>> getWikiDestinations();

    /**
     * 天气状况
     * @return
     */
    @GET("api/wiki/destinations/infos/{id}.json")
    Observable<Weather> getWikiWeather(@Path("id") int id);

    @GET("api/trips/{id}.json")
    Observable<HomeTripDetail> getHomeTripDetail(@Path("id") int id);
}
