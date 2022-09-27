package com.appcrud.amobasoftwaredemo.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appcrud.amobasoftwaredemo.ApiModels.LoginResponseModel;
import com.appcrud.amobasoftwaredemo.ApiModels.UsuarioModel;
import com.appcrud.amobasoftwaredemo.R;
import com.appcrud.amobasoftwaredemo.Utils.ApiUtils;
import com.appcrud.amobasoftwaredemo.Utils.AppRoutes;
import com.appcrud.amobasoftwaredemo.Utils.Loaders;
import com.appcrud.amobasoftwaredemo.Utils.Services;
import com.appcrud.amobasoftwaredemo.Utils.Sessions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallePacienteActivity extends AppCompatActivity {

    private TextView txvNombrePaciente;
    private TextView txvIdentificacion;
    private TextView txvMailPaciente;
    private TextView txvGeneroPaciente;
    private TextView txvDireccionPaciente;
    private TextView txvTelefonoPaciente;
    private TextView txvCelularPaciente;
    private ImageView imgCerrar;
    private TextView txvEdad;


    protected Services service = ApiUtils.getInstance();
    private Loaders loaders = new Loaders();
    private LoginResponseModel loginResponseModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_paciente_activity);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void init(){
        loaders.inicializaProgress(DetallePacienteActivity.this);
        loginResponseModel = Sessions.obtieneDatosLogin(DetallePacienteActivity.this);

        txvNombrePaciente = findViewById(R.id.txvNombrePaciente);
        txvIdentificacion = findViewById(R.id.txvIdentificacion);
        txvMailPaciente = findViewById(R.id.txvMailPaciente);
        txvGeneroPaciente = findViewById(R.id.txvGeneroPaciente);
        txvDireccionPaciente = findViewById(R.id.txvDireccionPaciente);
        txvTelefonoPaciente = findViewById(R.id.txvTelefonoPaciente);
        txvCelularPaciente = findViewById(R.id.txvCelularPaciente);
        imgCerrar = findViewById(R.id.imgCerrar);
        imgCerrar.setOnClickListener(view -> AppRoutes.goToMenu(DetallePacienteActivity.this));
        txvEdad = findViewById(R.id.txvEdad);

        obtenerPerfil();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void obtenerPerfil() {

        Bundle bundle = this.getIntent().getExtras();

        String nombre = bundle.getString("nombre");
        txvNombrePaciente.setText(nombre);

        String cedula = bundle.getString("cedula");
        txvIdentificacion.setText(cedula);

        String sexo = bundle.getString("sexo");
        txvGeneroPaciente.setText(sexo);



        String celular = bundle.getString("celular");
        txvCelularPaciente.setText(celular);

        String telefono = bundle.getString("telefono");
        txvTelefonoPaciente.setText(telefono);

        String direccion = bundle.getString("direccion");
        txvDireccionPaciente.setText(direccion);

        String email = bundle.getString("email");
        txvMailPaciente.setText(email);

        String fechanacimiento = bundle.getString("fechanacimiento");

        try{
            String strEdad = calcularEdad(fechanacimiento);
            txvEdad.setText(strEdad);
        }catch (ParseException e){
            e.printStackTrace();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public String calcularEdad(String fechaNacimiento) throws ParseException {

        String strEdad = "";

        LocalDate date = LocalDate.parse(fechaNacimiento);

        String strFecha = "";

        String strDia = "";
        String strMes = "";

        if(date.getMonthValue() < 10){
            strMes = "0"+date.getMonthValue();
        }else{
            strMes = String.valueOf(date.getMonthValue());
        }

        if(date.getDayOfMonth() < 10){
            strDia = "0"+date.getDayOfMonth();
        }else{
            strDia = String.valueOf(date.getDayOfMonth());
        }


        strFecha = strDia+"/"+strMes+"/"+date.getYear();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");



        LocalDate fechaNac = LocalDate.parse(strFecha, fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);


        if(periodo.getDays() < 1 && periodo.getMonths() < 1 && periodo.getYears() < 1 ){
            strEdad ="NACIO EL DÍA DE HOY";
        }else{

            if (periodo.getYears() < 1) {

                if (periodo.getMonths() < 2) {
                    if(periodo.getMonths() < 1 ){
                        if(periodo.getDays() == 1 ){
                            strEdad = periodo.getDays() + " DÍA";
                        }else{
                            strEdad = periodo.getDays() + " DÍAS";
                        }
                    }else{
                        strEdad = periodo.getMonths() + " MES";
                    }

                } else {
                    strEdad = periodo.getMonths() + " MESES";
                }

            } else if(periodo.getYears() > 1 ) {

                if(periodo.getYears() > 1 && periodo.getMonths() < 2){
                    strEdad = periodo.getYears()+" AÑOS y "+ periodo.getMonths() + " MES";
                }else if(periodo.getYears() > 1 && periodo.getMonths() > 1){
                    strEdad = periodo.getYears()+" AÑOS y "+ periodo.getMonths() + " MESES";
                }

            }

        }



        return strEdad;

    }

}