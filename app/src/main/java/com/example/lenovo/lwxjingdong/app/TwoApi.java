package com.example.lenovo.lwxjingdong.app;


import com.example.lenovo.lwxjingdong.bean.TwoBean;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by admin on 2018/9/9.
 */

public interface TwoApi {
    @POST("product/getCatagory")
    Observable<TwoBean> feilei();
}
