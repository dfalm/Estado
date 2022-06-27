package com.inicio.ui.estado

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.server.converter.StringToIntConverter
import com.inicio.R
import com.inicio.databinding.FragmentAddEstadoBinding
import com.inicio.model.Estado
import com.inicio.viewmodel.CentralViewModel


class AddEstadoFragment : Fragment() {
    private lateinit var estadoViewModel: CentralViewModel
    private var _binding: FragmentAddEstadoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        estadoViewModel = ViewModelProvider(this)[CentralViewModel::class.java]
        _binding = FragmentAddEstadoBinding.inflate(inflater,container,false)

        //se agrega la funcion para agregar un estado
        binding.btAdd.setOnClickListener{
            addEstado()
        }

        return binding.root
    }

    private fun addEstado() {
        val nombre = binding.etNombre.text.toString()
        val capital = binding.etCapital.text.toString()
        val poblacion = binding.etPoblacion.text.toString()
        val costas = binding.etCostas.text.toString()
        if(nombre.isNotEmpty()){
            val estado  = Estado(0,nombre,capital,Integer.parseInt(poblacion),costas)
            estadoViewModel.addEstado(estado)
            Toast.makeText(requireContext(),getString(R.string.centralAdded), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addEstadoFragment_to_nav_central)
        }else{
            Toast.makeText(requireContext(),getString(R.string.noData), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
}