package com.appexamene.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.appexamene.model.Examen


@Dao
interface ExamenDao { 
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
suspend fun addExamen(examen: Examen)

@Update(onConflict = OnConflictStrategy.IGNORE)
suspend fun updateExamen(examen: Examen)


/* para el examen */
@Delete
suspend fun deleteExamen(examen: Examen)

@Query("SELECT * FROM EXAMEN")
fun getAllData() : LiveData<List<Examen>>


}