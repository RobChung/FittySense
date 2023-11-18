package project.st991570719.rob.st991444103.sean.database.cardio

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cardio_workout")
data class CardioWorkout (
    @PrimaryKey(autoGenerate = true)
    var cardioId: Long = 0L,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "cardioName")
    val cardioName: String,

    @ColumnInfo(name = "distance")
    var distance: Double
)
