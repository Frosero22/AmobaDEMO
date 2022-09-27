package com.appcrud.amobasoftwaredemo.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.appcrud.amobasoftwaredemo.ApiModels.LoginResponseModel;

public class Sessions {

    //AQUI MANEJO LAS SESIONES EN MEMORIA

    public static void guardarLogin(LoginResponseModel loginResponse, Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("CRENDECIALES_USUARIO", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token",loginResponse.token);
        editor.apply();
    }

    public static void borrarLogin(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("CRENDECIALES_USUARIO", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


    public static LoginResponseModel obtieneDatosLogin(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("CRENDECIALES_USUARIO", Context.MODE_PRIVATE);
        LoginResponseModel datosLogin = new LoginResponseModel();
        datosLogin.token = sharedPreferences.getString("token","");
        return datosLogin;
    }

}
