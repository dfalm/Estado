package com.appexamene.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.sql.Date


@Parcelize
@Entity(tableName="examen")
data class Examen(
    @PrimaryKey(autoGenerate = true)
val idExamen: Int,
    @ColumnInfo(name="nombre")
val nombre:String,
    @ColumnInfo(name="dia")
val dia: String?,
    @ColumnInfo(name="nota")
val nota: Double?,
): Parcelable
