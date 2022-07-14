package com.appexamene

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.appexamene.adapter.ExamenAdapter
import com.appexamene.databinding.FragmentHomeBinding
import com.appexamene.viewmodel.ExamenViewModel

class HomeFragment : Fragment() {

    private lateinit var examenViewModel: ExamenViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        examenViewModel = ViewModelProvider(this)[ExamenViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
/*
        //Se programa la accion para pasarse a AddExamen
        binding.addExamenButton.setOnClickListener{
            findNavController().navigate(R.id.action)
        }

 */       //Activar el Reciclador -RecyclerView
        val examenAdapter = ExamenAdapter()
        val reciclador = binding.reciclador

        reciclador.adapter = examenAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        examenViewModel = ViewModelProvider(this)[ExamenViewModel::class.java]
        examenViewModel.getAllData.observe(viewLifecycleOwner){
                examenes -> examenAdapter.setData(examenes)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}