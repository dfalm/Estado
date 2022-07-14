package com.appexamene.repository

import androidx.lifecycle.LiveData
import com.appexamene.data.ExamenDao
import com.appexamene.model.Examen

class ExamenRepository(private val examenDao: ExamenDao) {
    //Se crea un objeto que contiene el array listo de los registros de la tabla examen.... cubiertos por
    val getAllData: LiveData<List<Examen>> = examenDao.getAllData()

    //se define la funcion para insertar un examen en la tabla examen
    suspend fun addExamen(examen: Examen) {
        examenDao.addExamen(examen)
    }

    //se define la funcion para actualizar un examen en la tabla examen
    suspend fun updateExamen(examen: Examen) {
        examenDao.updateExamen(examen)
    }

    //se define la funcion para eliminar un examen en la tabla examen
    suspend fun deleteExamen(examen: Examen) {
        examenDao.deleteExamen(examen)
    }
}