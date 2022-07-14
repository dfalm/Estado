package com.galart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.galart.databinding.EmpleadoFilaBinding
import com.galart.model.Empleado
import com.galart.ui.galart.GalartFragmentDirections

class EmpleadoAdapter : RecyclerView.Adapter<EmpleadoAdapter.EmpleadoViewHolder>(){
    //una lista para almacenar un alista de empleadoes
    private var listaEmpleadoes = emptyList<Empleado>()

    inner class EmpleadoViewHolder(private val itemBinding: EmpleadoFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(empleado: Empleado){
            itemBinding.tvNombre.text = empleado.nombre
            itemBinding.tvCorreo.text = empleado.correo
            itemBinding.tvTelefono.text = empleado.telefono
            itemBinding.tvWeb.text = empleado.web
            itemBinding.vistaFila.setOnClickListener{
               val accion = GalartFragmentDirections.actionNavGalartToUpdateEmpleadoFragment(empleado)
                itemView.findNavController().navigate(accion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpleadoViewHolder {
        val itemBinding = EmpleadoFilaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EmpleadoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: EmpleadoViewHolder, position: Int) {
        val empleado = listaEmpleadoes[position]
        holder.dibuja(empleado)
    }

    override fun getItemCount(): Int {
        return listaEmpleadoes.size
    }

    fun setData(empleadoes : List<Empleado>){
        this.listaEmpleadoes = empleadoes
        //La siguiente instrucción redibuja toda la lista del reciclador
        notifyDataSetChanged()
    }
}