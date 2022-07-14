package com.appexamene.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.appexamene.databinding.ExamenFilaBinding
import com.appexamene.model.Examen

class ExamenAdapter : RecyclerView.Adapter<ExamenAdapter.ExamenViewHolder>(){
    //una lista para almacenar un alista de examenes
    private var listaExamenes = emptyList<Examen>()

    inner class ExamenViewHolder(private val itemBinding: ExamenFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(examen: Examen){
            itemBinding.tvNombre.text = examen.nombre
            itemBinding.tvDia.text = examen.dia.toString()
            itemBinding.tvNota.text = examen.nota.toString()
            itemBinding.vistaFila.setOnClickListener{
               // val accion = ExamenFragmentDirections.actionNavGalartToAddExamenFragment(examen)
                //itemView.findNavController().navigate(accion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamenViewHolder {
        val itemBinding = ExamenFilaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ExamenViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ExamenViewHolder, position: Int) {
        val examen = listaExamenes[position]
        holder.dibuja(examen)
    }

    override fun getItemCount(): Int {
        return listaExamenes.size
    }

    fun setData(examenes : List<Examen>){
        this.listaExamenes = examenes
        //La siguiente instrucción redibuja toda la lista del reciclador
        notifyDataSetChanged()
    }
}

