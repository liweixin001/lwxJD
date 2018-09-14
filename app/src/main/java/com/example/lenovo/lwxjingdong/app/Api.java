package com.example.lenovo.lwxjingdong.app;

import com.example.lenovo.lwxjingdong.bean.FristBean;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by admin on 2018/9/8.
 */

public interface Api {
    @POST("home/getHome")
    Observable<FristBean> shouye();
}
