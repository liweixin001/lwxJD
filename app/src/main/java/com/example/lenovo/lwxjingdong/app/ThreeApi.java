package com.example.lenovo.lwxjingdong.app;

import com.example.lenovo.lwxjingdong.bean.ThreeBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lenovo on 2018/9/13.
 */

public interface ThreeApi {
    @POST("product/getCarts")
    @FormUrlEncoded
    Observable<ThreeBean> shopping(@FieldMap HashMap<String, String> prams);
}
