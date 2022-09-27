package com.appcrud.amobasoftwaredemo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.appcrud.amobasoftwaredemo.Activity.DetallePacienteActivity;
import com.appcrud.amobasoftwaredemo.ApiModels.UsuarioModel;
import com.appcrud.amobasoftwaredemo.R;
import com.appcrud.amobasoftwaredemo.Utils.AppRoutes;

import java.util.List;

public class PacientesAdapter extends RecyclerView.Adapter<PacientesAdapter.HolderUsuarios> implements View.OnClickListener{

    private List<UsuarioModel> lsUsuarios;
    private UsuarioModel usuarioModel;
    private static Context context;

    private View.OnClickListener listener;


    public PacientesAdapter(List<UsuarioModel> lsUsuarios,Context context){
        this.lsUsuarios = lsUsuarios;
        this.context = context;
    }

    @Override
    public PacientesAdapter.HolderUsuarios onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.paciente_actual_model,parent,false);
        v.setOnClickListener(this);
        return new HolderUsuarios(v);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull HolderUsuarios holder, int position) {
        holder.txvNombrePaciente.setText(lsUsuarios.get(position).nombre);
        holder.imgVerDetalle.setOnClickListener(view -> AppRoutes.goToDetalle(context,lsUsuarios.get(position)));
    }

    @Override
    public int getItemCount() {
        return lsUsuarios.size();
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class HolderUsuarios extends RecyclerView.ViewHolder{

        ImageView imgVerDetalle;
        TextView txvNombrePaciente;

        public HolderUsuarios(@NonNull View itemView){
            super(itemView);

            txvNombrePaciente = itemView.findViewById(R.id.nombrePaciente);
            imgVerDetalle = itemView.findViewById(R.id.verDetalle);

        }

    }



}
