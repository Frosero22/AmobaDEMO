package com.appcrud.amobasoftwaredemo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginModel {

    @JsonProperty("celular")
    private String celular;

    @JsonProperty("email")
    private String email;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("cedula")
    private String cedula;

    @JsonProperty("clave")
    private String clave;

    @JsonProperty("telefono")
    private String telefono;

    @JsonProperty("sexo")
    private String sexo;

    @JsonProperty("fechanacimiento")
    private String fechanacimiento;


}
