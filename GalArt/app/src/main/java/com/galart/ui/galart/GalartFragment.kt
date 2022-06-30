package com.galart.ui.galart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.galart.databinding.FragmentGalartBinding
import com.galart.viewmodel.EmpleadoViewModel

class GalartFragment : Fragment() {

    private var _binding: FragmentGalartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val empleadoViewModel =
            ViewModelProvider(this).get(EmpleadoViewModel::class.java)

        _binding = FragmentGalartBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}