package com.example.lenovo.lwxjingdong.app;

import com.example.lenovo.lwxjingdong.bean.DetailsBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by lenovo on 2018/9/14.
 */

public interface DetailApi {
    @POST()
    @FormUrlEncoded
    Observable<DetailsBean>detail(@FieldMap HashMap<String,String>map);
}

