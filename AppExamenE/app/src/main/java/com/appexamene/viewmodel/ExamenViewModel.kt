package com.appexamene.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.appexamene.data.ExamenDatabase
import com.appexamene.model.Examen
import com.appexamene.repository.ExamenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExamenViewModel (application: Application) : AndroidViewModel(application) {

    val getAllData: LiveData<List<Examen>>

    //Esta es la manera para acceder al repositorio desde el viewmodel
    private val repository: ExamenRepository

    //Se inicializan los atributos de la clase viewModel
    init{
        val examenDao = ExamenDatabase.getDatabase(application).examenDao()
        repository = ExamenRepository(examenDao)
        getAllData = repository.getAllData
    }

    //Esta funcion de alto nivel llama al subproceso de I/O para grabar un registro Examen
    fun addExamen(examen : Examen){

        viewModelScope.launch(Dispatchers.IO){

            repository.addExamen(examen)
        }

    }

    //Esta funcion de alto nivel llama al subproceso de I/O para actualizar un registro Examen
    fun updateExamen(examen : Examen){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateExamen(examen)
        }
    }

    //Esta funcion de alto nivel llama al subproceso de I/O para eliminar un registro Examen
    fun deleteExamen(examen : Examen){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteExamen(examen)
        }
    }

}