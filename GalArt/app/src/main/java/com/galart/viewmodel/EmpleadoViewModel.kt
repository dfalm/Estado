package com.galart.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.galart.data.EmpleadoDatabase
import com.galart.model.Empleado
import com.galart.repository.EmpleadoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmpleadoViewModel (application: Application) : AndroidViewModel(application) {

    val getAllData: LiveData<List<Empleado>>

    //Esta es la manera para acceder al repositorio desde el viewmodel
    private val repository: EmpleadoRepository

    //Se inicializan los atributos de la clase viewModel
    init{
        val empleadoDao = EmpleadoDatabase.getDatabase(application).empleadoDao()
        repository = EmpleadoRepository(empleadoDao)
        getAllData = repository.getAllData
    }

    //Esta funcion de alto nivel llama al subproceso de I/O para grabar un registro Empleado
    fun addEmpleado(empleado : Empleado){

        viewModelScope.launch(Dispatchers.IO){

            repository.addEmpleado(empleado)
        }

    }

    //Esta funcion de alto nivel llama al subproceso de I/O para actualizar un registro Empleado
    fun updateEmpleado(empleado : Empleado){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateEmpleado(empleado)
        }
    }

    //Esta funcion de alto nivel llama al subproceso de I/O para eliminar un registro Empleado
    fun deleteEmpleado(empleado : Empleado){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteEmpleado(empleado)
        }
    }

}