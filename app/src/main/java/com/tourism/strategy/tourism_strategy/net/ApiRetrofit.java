package com.tourism.strategy.tourism_strategy.net;

import com.tourism.strategy.tourism_strategy.model.Album;
import com.tourism.strategy.tourism_strategy.model.Attraction;
import com.tourism.strategy.tourism_strategy.model.AttractionDetail;
import com.tourism.strategy.tourism_strategy.model.AttractionTrips;
import com.tourism.strategy.tourism_strategy.model.Category;
import com.tourism.strategy.tourism_strategy.model.Plan;
import com.tourism.strategy.tourism_strategy.model.PlanDetail;
import com.tourism.strategy.tourism_strategy.model.Wiki;
import com.tourism.strategy.tourism_strategy.model.Zone;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofit {

    private static ApiRetrofit mInstance;
    private MyApi myApi;

    private ApiRetrofit() {
        myApi = new Retrofit.Builder()
                .baseUrl(MyApi.BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MyApi.class);
    }

    public static ApiRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (ApiRetrofit.class) {
                if (mInstance == null) {
                    mInstance = new ApiRetrofit();
                }
            }
        }
        return mInstance;
    }

    private OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        return client;
    }

    public Observable<List<Category>> getAreas(){
        return myApi.getAreas();
    }

    public Observable<List<Zone>> getCountry(int cid){
        return myApi.getCountry(cid);
    }

    public Observable<List<Attraction>> getAttractions(int cid, int page){
        return myApi.getAttractions(cid,page);
    }

    public Observable<AttractionDetail> getAttraction(int attractionId){
        return myApi.getAttraction(attractionId);
    }

    public Observable<List<Album>> getAlbum(int attractionId, int page){
        return myApi.getAlbum(attractionId,page);
    }

    public Observable<AttractionTrips> getTourism(int attractionId, int page){
        return myApi.getTourism(attractionId,page);
    }

    public Observable<List<Plan>> getPlans(int cid, int page){
        return myApi.getPlans(cid,page);
    }

    public Observable<PlanDetail> getPlanDetail(int planId){
        return myApi.getPlanDetail(planId);
    }

    public Observable<List<Wiki>> getWiki(int id){
        return myApi.getWiki(id);
    }

}
