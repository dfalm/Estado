package com.galart.ui.galart

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.galart.R
import com.galart.databinding.FragmentUpdateEmpleadoBinding
import com.galart.model.Empleado
import com.galart.viewmodel.EmpleadoViewModel


class UpdateEmpleadoFragment : Fragment() {


    private lateinit var empleadoViewModel: EmpleadoViewModel
    private var _binding: FragmentUpdateEmpleadoBinding? = null
    private val binding get() = _binding!!

    //Defino un argumento
    private val args by navArgs<UpdateEmpleadoFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        empleadoViewModel = ViewModelProvider(this)[EmpleadoViewModel::class.java]
        _binding = FragmentUpdateEmpleadoBinding.inflate(inflater,container,false)

        //Se coloca la info del objeto empleado que me pasaron
        binding.etNombre.setText(args.empleado.nombre)
        binding.etTelefono.setText(args.empleado.telefono)
        binding.etCorreo.setText(args.empleado.correo)
        binding.etWeb.setText(args.empleado.web)

        binding.tvAltura.text = args.empleado.altura.toString()
        binding.tvLatitud.text = args.empleado.latitud.toString()
        // binding.tvLongitud.text = args.empleado.longitud.toString()


        //agrega la funcion para update un empleado
        binding.btActualizar.setOnClickListener{ updateEmpleado()}

        binding.btEmail.setOnClickListener{escribirCorreo()}
        binding.btPhone.setOnClickListener{llamarEmpleado()}
        binding.btWeb.setOnClickListener{verWeb()}
/*
        binding.btWhatsapp.setOnClickListener{enviarWhatsapp()}
        binding.btLocation.setOnClickListener{verMapa()}
*/

        //Se indica que en esta pantalla se agrega una opcion de menu para el delete
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun verWeb() {
        val recurso = binding.etWeb.text.toString()
        if(recurso.isNotEmpty()){
            val rutina = Intent(Intent.ACTION_VIEW, Uri.parse("http://$recurso"))
            startActivity(rutina) // Levanta el browser
        }else{
            Toast.makeText(
                requireContext(),getString(R.string.msg_datos), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun llamarEmpleado() {
        //Se recupera el numero de telefono
        val recurso = binding.etTelefono.text.toString()
        if(recurso.isNotEmpty()){
            val rutina = Intent(Intent.ACTION_CALL)
            rutina.data = Uri.parse("tel:$recurso")
            if(requireActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                //Se solicitan los permisos pues no estan otorgados
                requireActivity().requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),105)
            }else{ // Se tienen los permisos para la llamada
                requireActivity().startActivity(rutina)
            }
        }else{
            Toast.makeText(
                requireContext(),getString(R.string.msg_datos), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun escribirCorreo() {
        val recurso = binding.etCorreo.text.toString()
        if(recurso.isNotEmpty()){
            //Se activa el correo
            val rutina = Intent(Intent.ACTION_SEND)
            rutina.type="message/rfc822"
            rutina.putExtra(Intent.EXTRA_EMAIL, arrayOf(recurso))
            rutina.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.msg_saludos) + " " + binding.etNombre.text)
            rutina.putExtra(Intent.EXTRA_TEXT, getString(R.string.msg_mensaje_correo))
            startActivity(rutina) //Levanta el correo y lo presenta para modificar y enviar
        }else{
            Toast.makeText(
                requireContext(),getString(R.string.msg_datos), Toast.LENGTH_SHORT
            ).show()
        }
    }

    //Se genera el menu con el icono de borrar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    //Se programa que si se detecta un click se elimine el registro
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Pregunto si se dio click en el icono de borrado
        if(item.itemId == R.id.menu_delete){
            //hace algo si se dio click
            deleteEmpleado()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteEmpleado() {
        val consulta = AlertDialog.Builder(requireContext())
        consulta.setTitle(R.string.delete)
        consulta.setMessage(getString(R.string.seguroBorrar) + "${args.empleado.nombre}?")

        //Acciones a ejecutar si respondo YES
        consulta.setPositiveButton(getString(R.string.si)) { _, _ ->
            //Se borra el empleado..sin consultar
            empleadoViewModel.deleteEmpleado(args.empleado)
            findNavController().navigate(R.id.action_updateEmpleadoFragment_to_nav_galart)
        }

        consulta.setNegativeButton(getString(R.string.no)) {_,_ ->}
        consulta.create().show()
    }

    private fun updateEmpleado() {
        val nombre = binding.etNombre.text.toString()
        val correo = binding.etCorreo.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()
        if(nombre.isNotEmpty()){
            val empleado = Empleado(args.empleado.id,nombre,correo,telefono,web,0.0,0.0, 0.0,"", "")
            empleadoViewModel.updateEmpleado(empleado)
            Toast.makeText(requireContext(),getString(R.string.empleadoAdded), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateEmpleadoFragment_to_nav_galart)
        }else{
            Toast.makeText(requireContext(),getString(R.string.noData), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}