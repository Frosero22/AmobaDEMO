package com.appcrud.amobasoftwaredemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appcrud.amobasoftwaredemo.ApiModels.LoginRequestModel;
import com.appcrud.amobasoftwaredemo.ApiModels.LoginResponseModel;
import com.appcrud.amobasoftwaredemo.R;
import com.appcrud.amobasoftwaredemo.Utils.ApiUtils;
import com.appcrud.amobasoftwaredemo.Utils.AppRoutes;
import com.appcrud.amobasoftwaredemo.Utils.Loaders;
import com.appcrud.amobasoftwaredemo.Utils.Messages;
import com.appcrud.amobasoftwaredemo.Utils.Services;
import com.appcrud.amobasoftwaredemo.Utils.Sessions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText et_user;
    private EditText et_pass;
    private Button btn_iniciar_sesion;


    //VARIABLE QUE INICIALIZA RETROFIT
    protected Services service = ApiUtils.getInstance();

    //VARIABLE QUE INICIALIZA EL PROGRESS DIALOG, YO LO PONGO EN UNA CLASE PARA NO ESTAR ESCRIBIENDO MUCHO CODIGO, REUTILIZARLO MAS BIEN
    private Loaders loaders = new Loaders();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //AQUI VALIDO QUE  HAYA ALGUN TOKEN GUARDADO, EN CASO DE QUE SI, QUIERE DECIR QUE EL USUARIO YA SE LOGUEO

        LoginResponseModel loginResponseModel = Sessions.obtieneDatosLogin(LoginActivity.this);
        if(loginResponseModel.token != null && !loginResponseModel.token.equalsIgnoreCase("")){
            AppRoutes.goToMenu(LoginActivity.this);
        }else{
            init();
        }

    }

    public void init(){
        loaders.inicializaProgress(LoginActivity.this);

        et_user = findViewById(R.id.et_user);
        et_pass = findViewById(R.id.et_pass);
        btn_iniciar_sesion = findViewById(R.id.btn_iniciar_sesion);

        btn_iniciar_sesion.setOnClickListener(view -> {
            if(valida()){
                login(et_user.getText().toString(),et_pass.getText().toString());
            }
        });
    }

    public boolean valida(){

        if(et_user.getText() == null || et_user.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(LoginActivity.this, "El usuario no puede ir vacio", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(et_pass.getText() == null || et_pass.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(LoginActivity.this, "La clave no puede ir vacia", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }


    public void login(String user, String pass){

        loaders.muestraProgress();
        loaders.mensaje("Iniciando Sesión...");

        //ARMO OBJETO PARA LOGUEARME

        LoginRequestModel loginRequestModel = new LoginRequestModel();
        loginRequestModel.usuario = user;
        loginRequestModel.clave = pass;




        Call<LoginResponseModel> call = service.login(loginRequestModel);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                loaders.cierraProgress();
                if(response.code() == 200){
                    LoginResponseModel loginResponse = response.body();
                    if(loginResponse != null){

                        //EN CASO DE QUE EL CAMPO MENSAJE VENGA LLENO, QUIERE DECIR QUE HUBO UN ERROR, CASO CONTRARIO, NOS LLEVA AL LISTADO
                        //GUARDANDO EL TOKEN EN SESION PARA MANTENER LA SESION ACTIVA

                        if(loginResponse.message != null){
                            Messages.mensaje(LoginActivity.this,loginResponse.message);
                        }else{
                            if(loginResponse.token != null){
                                Sessions.guardarLogin(loginResponse,LoginActivity.this);
                                AppRoutes.goToMenu(LoginActivity.this);
                            }
                        }
                    }


                }else{
                    Messages.mensaje(LoginActivity.this,"Usuario y/o Clave incorrecta");
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                loaders.cierraProgress();
                Messages.mensaje(LoginActivity.this,"Hubo un error y devolvio lo siguiente "+t.getMessage());
            }
        });

    }

}