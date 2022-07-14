package com.appexamene.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appexamene.model.Examen

@Database(entities=[Examen::class], version = 1, exportSchema = false)
abstract class ExamenDatabase : RoomDatabase(){

    abstract fun examenDao() : ExamenDao
    companion object{
        @Volatile
        private var INSTANCE: ExamenDatabase? = null

        fun getDatabase(context: android.content.Context) : ExamenDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExamenDatabase::class.java,
                    "examen_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}