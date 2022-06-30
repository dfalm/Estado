package com.galart.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.galart.model.Empleado


@Dao
interface EmpleadoDao {
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun addEmpleado(empleado : Empleado)


    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateEmpleado(empleado : Empleado)


    @Delete
    suspend fun deleteEmpleado(empleado : Empleado)

    @Query("SELECT * FROM EMPLEADO")
    fun getAllData() : LiveData<List<Empleado>>
}