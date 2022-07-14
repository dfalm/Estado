package com.galart.ui.galart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.galart.R
import com.galart.adapter.EmpleadoAdapter
import com.galart.databinding.FragmentGalartBinding
import com.galart.viewmodel.EmpleadoViewModel

class GalartFragment : Fragment() {

    private lateinit var empleadoViewModel: EmpleadoViewModel
    private var _binding: FragmentGalartBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        empleadoViewModel = ViewModelProvider(this)[EmpleadoViewModel::class.java]
        _binding = FragmentGalartBinding.inflate(inflater,container,false)

        //Se programa la accion para pasarse a AddEmpleado
        binding.addEmpleadoButton.setOnClickListener{
            findNavController().navigate(R.id.action_nav_galart_to_addEmpleadoFragment)
        }

        //Activar el Reciclador -RecyclerView
        val empleadoAdapter = EmpleadoAdapter()
        val reciclador = binding.reciclador

        reciclador.adapter = empleadoAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        empleadoViewModel = ViewModelProvider(this)[EmpleadoViewModel::class.java]
        empleadoViewModel.getAllData.observe(viewLifecycleOwner){
                empleadoes -> empleadoAdapter.setData(empleadoes)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}