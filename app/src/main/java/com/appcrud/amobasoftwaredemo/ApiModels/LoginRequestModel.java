package com.appcrud.amobasoftwaredemo.ApiModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

public class LoginRequestModel {

    @JsonProperty("usuario")
    public String usuario;
    @JsonProperty("clave")
    public String clave;



}
