package com.appcrud.amobasoftwaredemo.Utils;

import com.appcrud.amobasoftwaredemo.ApiModels.LoginRequestModel;
import com.appcrud.amobasoftwaredemo.ApiModels.LoginResponseModel;
import com.appcrud.amobasoftwaredemo.ApiModels.UsuarioModel;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Services {

    @POST("api/login")
    Call<LoginResponseModel> login(@Body LoginRequestModel loginRequestModel);

    @GET("api/getUsuarios")
    Call<List<UsuarioModel>> getUsers(@Header("Authorization")String token);

    @GET("api/profile")
    Call<UsuarioModel> getProfile(@Header("Authorization")String token);

    @GET("api/addUsuarios")
    Call<UsuarioModel> addUsers(@Header("Authorization")String token);

}
