package project.st991570719.rob.st991444103.sean.database.lifting

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lifting_workout")
data class LiftingWorkout (
    @PrimaryKey(autoGenerate = true)
    var liftingId: Long = 0L,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "lifting_name")
    val liftingName: String,

    @ColumnInfo(name = "sets")
    var sets: Int,

    @ColumnInfo(name = "reps")
    var reps: Int,

    @ColumnInfo(name = "weight")
    var weight: Double
)
