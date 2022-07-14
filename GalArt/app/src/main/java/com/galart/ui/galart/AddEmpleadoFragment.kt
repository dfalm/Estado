package com.galart.ui.galart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.galart.R
import com.galart.databinding.FragmentAddEmpleadoBinding
import com.galart.model.Empleado
import com.galart.viewmodel.EmpleadoViewModel


class AddEmpleadoFragment : Fragment() {



    private lateinit var empleadoViewModel: EmpleadoViewModel
    private var _binding: FragmentAddEmpleadoBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        empleadoViewModel = ViewModelProvider(this)[EmpleadoViewModel::class.java]
        _binding = FragmentAddEmpleadoBinding.inflate(inflater,container,false)

        //se agrega la funcion para agregar un empleado
        binding.btAdd.setOnClickListener{
            addEmpleado()
        }

        return binding.root
    }

    private fun addEmpleado() {
        val nombre = binding.etNombre.text.toString()
        val correo = binding.etCorreo.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()
        if(nombre.isNotEmpty()){
            val empleado = Empleado(0,nombre,correo,telefono,web,0.0,0.0, 0.0,"","")
            empleadoViewModel.addEmpleado(empleado)
            Toast.makeText(requireContext(),getString(R.string.empleadoAdded), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addEmpleadoFragment_to_nav_galart)
        }else{
            Toast.makeText(requireContext(),getString(R.string.noData), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}