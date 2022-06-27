package com.inicio.repository

import androidx.lifecycle.LiveData
import com.inicio.data.EstadoDao
import com.inicio.model.Estado

class EstadoRepository(private val estadoDao: EstadoDao) {
    //Se crea un objeto que contiene el array listo de los registros de la tabla estado.... cubiertos por
    val getAllData: LiveData<List<Estado>> = estadoDao.getAllData()

    //se define la funcion para insertar un estado en la tabla estado
    suspend fun addEstado(estado: Estado) {
        estadoDao.addEstado(estado)
    }

    //se define la funcion para actualizar un estado en la tabla estado
    suspend fun updateEstado(estado:Estado) {
        estadoDao.updateEstado(estado)
    }

    //se define la funcion para eliminar un estado en la tabla estado
    suspend fun deleteEstado(estado:Estado) {
        estadoDao.deleteEstado(estado)
    }
}