package com.u3coding.rxjavatest;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ${U3} on 2017/3/3.
 */

public interface MovieService {
    @GET("top250")
    Observable<MovieEntity> getTopMoive(@Query("start") int start, @Query("count") int count);
}
