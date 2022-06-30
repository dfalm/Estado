package com.galart.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.galart.model.Empleado

@Database(entities=[Empleado::class], version = 1, exportSchema = false)
abstract class EmpleadoDatabase : RoomDatabase() {
    abstract fun empleadoDao() : EmpleadoDao
    companion object{
        @Volatile
        private var INSTANCE: EmpleadoDatabase? = null

        fun getDatabase(context: android.content.Context) : EmpleadoDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmpleadoDatabase::class.java,
                    "empleado_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}