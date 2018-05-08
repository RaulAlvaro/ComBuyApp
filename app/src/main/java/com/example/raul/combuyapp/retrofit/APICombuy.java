package com.example.raul.combuyapp.retrofit;

import com.example.raul.combuyapp.models.LocalNegocio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by raul on 24/04/18.
 */

public interface APICombuy {
    @GET("localnegocio/")
    Call<List<LocalNegocio>> loadChanges();
}
