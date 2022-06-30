package com.galart.repository

import androidx.lifecycle.LiveData
import com.galart.data.EmpleadoDao
import com.galart.model.Empleado

class EmpleadoRepository(private val empleadoDao: EmpleadoDao) {

        //Se crea un objeto que contiene el array listo de los registros de la tabla empleado.... cubiertos por
        val getAllData: LiveData<List<Empleado>> = empleadoDao.getAllData()

        //se define la funcion para insertar un empleado en la tabla empleado
        suspend fun addEmpleado(empleado:Empleado) {
            empleadoDao.addEmpleado(empleado)
        }

        //se define la funcion para actualizar un empleado en la tabla empleado
        suspend fun updateEmpleado(empleado:Empleado) {
            empleadoDao.updateEmpleado(empleado)
        }

        //se define la funcion para eliminar un empleado en la tabla empleado
        suspend fun deleteEmpleado(empleado:Empleado) {
            empleadoDao.deleteEmpleado(empleado)
        }
    }
