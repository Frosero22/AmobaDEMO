package com.appcrud.amobasoftwaredemo.ApiModels;

import com.appcrud.amobasoftwaredemo.Models.LoginModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class LoginResponseModel {

    public LoginModel datos;
    public String token;
    @JsonProperty("message")
    public String message;


}
