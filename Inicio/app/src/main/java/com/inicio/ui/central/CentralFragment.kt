package com.inicio.ui.central

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.inicio.R
import com.inicio.databinding.FragmentAddEstadoBinding
import com.inicio.databinding.FragmentCentralBinding
import com.inicio.model.Estado
import com.inicio.viewmodel.CentralViewModel

class CentralFragment : Fragment() {

    private lateinit var centralViewModel: CentralViewModel
    private var _binding: FragmentCentralBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        centralViewModel = ViewModelProvider(this)[CentralViewModel::class.java]
        _binding = FragmentCentralBinding.inflate(inflater,container,false)


        binding.addEstadoButton.setOnClickListener{
            findNavController().navigate(R.id.action_nav_central_to_addEstadoFragment)
        }
        /*
        //Activar el Reciclador -RecyclerView
        val lugarAdapter = LugarAdapter()
        val reciclador = binding.reciclador

        reciclador.adapter = lugarAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        lugarViewModel = ViewModelProvider(this)[LugarViewModel::class.java]
        lugarViewModel.getAllData.observe(viewLifecycleOwner){
                lugares -> lugarAdapter.setData(lugares)
        }

*/
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}