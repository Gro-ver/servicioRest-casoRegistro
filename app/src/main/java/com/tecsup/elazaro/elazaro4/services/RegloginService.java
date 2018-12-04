package com.tecsup.elazaro.elazaro4.services;

import com.tecsup.elazaro.elazaro4.models.Registro;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RegloginService {

    //debe ir colocado la url donde esta alojado el web server
    String API_BASE_URL = "http://elazaro4-eduardfenix.c9users.io";

    @GET("/reglogin/registros/")
    Call<List<Registro>> getRegistros();

    @FormUrlEncoded
    @POST("/reglogin/registros/")
    Call< Registro> Login(@Field("nombre") String nombre,
                          @Field("email") String email,
                          @Field("password") String password,
                          @Field("tipousuario") String tipousuario);


}
