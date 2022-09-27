package com.appcrud.amobasoftwaredemo.Utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Messages {

    //UNA CLASE PARA MOSTRAR MENSAJES

    public static void mensaje(Context context, String strMensaje){

        new AlertDialog.Builder(context)
                .setTitle("Info")
                .setMessage(strMensaje)
                //  .setIcon(R.drawable.advertencia)
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();


    }

}
