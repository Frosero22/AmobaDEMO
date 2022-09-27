package com.appcrud.amobasoftwaredemo.Utils;

import android.content.Context;
import android.content.Intent;

import com.appcrud.amobasoftwaredemo.Activity.DetallePacienteActivity;
import com.appcrud.amobasoftwaredemo.Activity.LoginActivity;
import com.appcrud.amobasoftwaredemo.Activity.PacientesActualesActivity;
import com.appcrud.amobasoftwaredemo.ApiModels.UsuarioModel;

public class AppRoutes {

    //ESTA CLASE DE AQUI ES DONDE MANEJO TODAS LOS INTENTS, PARA QUE EN LOS ACTIVITY NO SE VEA TAN LLENO DE CODIGO

    public static void goToMenu(Context context){
        Intent intent = new Intent(context, PacientesActualesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void goToDetalle(Context context, UsuarioModel usuarioModel){
        Intent intent = new Intent(context, DetallePacienteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("nombre",usuarioModel.nombre);
        intent.putExtra("cedula",usuarioModel.cedula);
        intent.putExtra("sexo",usuarioModel.sexo);
        intent.putExtra("fechanacimiento",usuarioModel.fechanacimiento);
        intent.putExtra("celular",usuarioModel.celular);
        intent.putExtra("telefono",usuarioModel.telefono);
        intent.putExtra("direccion",usuarioModel.direccion);
        intent.putExtra("email",usuarioModel.email);
        context.startActivity(intent);
    }

    public static void goToLogin(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
