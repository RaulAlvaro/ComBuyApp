package com.example.raul.combuyapp.retrofit;

import android.util.Log;

import com.example.raul.combuyapp.PrincipalContract;
import com.example.raul.combuyapp.models.LocalNegocio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by raul on 24/04/18.
 */

public class LocalnegocioController {

    static final String BASE_URL = "";
    PrincipalContract principalContract;
    List<LocalNegocio> list;
    //Call<List<LocalNegocio>> listlocalnegocio;

    public void start(){
        list = new ArrayList<>();
        list.add(new LocalNegocio(01, -12.7000f, 71.0000f, "HOLA"));
        principalContract.getList(list);


        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APICombuy apiCombuy = retrofit.create(APICombuy.class);
        //Response<LocalNegocio> localnegocios = apiCombuy;

        Call<List<LocalNegocio>> listlocalnegocio = apiCombuy.loadChanges();
        Log.i("TAG", "ESTA FAKE ESTA WEAAA ALVVVVV");
        listlocalnegocio.enqueue(new Callback<List<LocalNegocio>>() {
            @Override
            public void onResponse(Call<List<LocalNegocio>> call, Response<List<LocalNegocio>> response) {
                if (!response.isSuccessful()){
                    Log.i("TAG", "Error" + response.code());
                }else {
                    Log.i("TAG", "ENTRA ALVVVVV" + response.code());
                    List<LocalNegocio> lnegocio= response.body();
                    for (int i =0; i<lnegocio.size();i++){
                        LocalNegocio localNegocio = lnegocio.get(i);
                        Log.i("FUNCIONA", localNegocio.getDescripcion());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<LocalNegocio>> call, Throwable t) {
                Log.i("TAG", "ENTRA AL ERROR ALVVVVV");
                Log.e("Error" , t.getMessage());
            }
        });

    }

}
