package com.inicio.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inicio.data.EstadoDatabase
import com.inicio.model.Estado
import com.inicio.repository.EstadoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CentralViewModel (application : Application) :  AndroidViewModel(application){
    val getAllData: LiveData<List<Estado>>

    //Esta es la manera para acceder al repositorio desde el viewmodel
    private val repository: EstadoRepository

    //Se inicializan los atributos de la clase viewModel
    init{
        val estadoDao = EstadoDatabase.getDatabase(application).estadoDao()
        repository = EstadoRepository(estadoDao)
        getAllData = repository.getAllData
    }

    //Esta funcion de alto nivel llama al subproceso de I/O para grabar un registro Estado
    fun addEstado(estado : Estado){

        viewModelScope.launch(Dispatchers.IO){

            repository.addEstado(estado)
        }

    }

    //Esta funcion de alto nivel llama al subproceso de I/O para actualizar un registro Estado
    fun updateEstado(estado : Estado){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateEstado(estado)
        }
    }

    //Esta funcion de alto nivel llama al subproceso de I/O para eliminar un registro Estado
    fun deleteEstado(estado : Estado){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteEstado(estado)
        }
    }
}