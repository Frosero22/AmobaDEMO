package com.appcrud.amobasoftwaredemo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.appcrud.amobasoftwaredemo.Adapters.PacientesAdapter;
import com.appcrud.amobasoftwaredemo.ApiModels.LoginResponseModel;
import com.appcrud.amobasoftwaredemo.ApiModels.UsuarioModel;
import com.appcrud.amobasoftwaredemo.R;
import com.appcrud.amobasoftwaredemo.Utils.ApiUtils;
import com.appcrud.amobasoftwaredemo.Utils.AppRoutes;
import com.appcrud.amobasoftwaredemo.Utils.Loaders;
import com.appcrud.amobasoftwaredemo.Utils.Messages;
import com.appcrud.amobasoftwaredemo.Utils.Services;
import com.appcrud.amobasoftwaredemo.Utils.Sessions;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PacientesActualesActivity extends AppCompatActivity {

    private ArrayList<UsuarioModel> lsUsuarios = new ArrayList<>();
    private ArrayList<UsuarioModel> lsAuxiliar = new ArrayList<>();

    private RecyclerView recyclerView;
    private PacientesAdapter pacientesAdapter;

    private LoginResponseModel loginResponseModel;

    protected Services service = ApiUtils.getInstance();
    private Loaders loaders = new Loaders();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pacientes_actuales_activity);
        init();
    }

    public void init(){
        getSupportActionBar().setTitle("Pacientes Actuales");

        loaders.inicializaProgress(PacientesActualesActivity.this);
        recyclerView = findViewById(R.id.recPacientesActuales);
        registerForContextMenu(recyclerView);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);


        lsUsuarios = new ArrayList<>();
        lsAuxiliar = new ArrayList<>();

        pacientesAdapter = new PacientesAdapter(lsUsuarios,PacientesActualesActivity.this);
        recyclerView.setAdapter(pacientesAdapter);

        loginResponseModel = Sessions.obtieneDatosLogin(PacientesActualesActivity.this);

        obtenerListado();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem cerrarSesion = menu.findItem(R.id.cerrarSesion);
        MenuItem mensaje = menu.findItem(R.id.mensaje);


        cerrarSesion.setOnMenuItemClickListener(item -> {
            Sessions.borrarLogin(PacientesActualesActivity.this);
            AppRoutes.goToLogin(PacientesActualesActivity.this);
            return false;
        });

        mensaje.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Messages.mensaje(PacientesActualesActivity.this,"De antemano muchas gracias por esta oportunidad, de realizar esta prueba " +
                        " a decir verdad fue bastante divertida, mas que nada en el diseño, me gusto bastante y fue muy entretenido, espero que les guste " +
                        " mi trabajo, si llego a ser selecciónado o no, les agradezco por tomarme en cuenta, Dios los bendiga!");
                return false;
            }
        });

        return true;

    }

    public void obtenerListado(){

        loaders.muestraProgress();
        loaders.mensaje("Obtiendo Pacientes...");

        Call<List<UsuarioModel>> call = service.getUsers(loginResponseModel.token);
        call.enqueue(new Callback<List<UsuarioModel>>() {
            @Override
            public void onResponse(Call<List<UsuarioModel>> call, Response<List<UsuarioModel>> response) {
                if(response.code() == 200){
                    runOnUiThread(() -> {
                        loaders.cierraProgress();
                        pacientesAdapter = new PacientesAdapter(response.body(),PacientesActualesActivity.this);
                        recyclerView.setAdapter(pacientesAdapter);
                        pacientesAdapter.notifyDataSetChanged();
                    });
                }else{
                    obtenerListado();
                }
            }

            @Override
            public void onFailure(Call<List<UsuarioModel>> call, Throwable t) {
                loaders.cierraProgress();
                Messages.mensaje(PacientesActualesActivity.this,"Hubo un error y devolvio lo siguiente "+t.getMessage());
            }
        });

    }
}