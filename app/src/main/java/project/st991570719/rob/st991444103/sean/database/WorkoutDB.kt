package project.st991570719.rob.st991444103.sean.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import project.st991570719.rob.st991444103.sean.database.cardio.CardioWorkout
import project.st991570719.rob.st991444103.sean.database.cardio.CardioWorkoutDao
import project.st991570719.rob.st991444103.sean.database.lifting.LiftingWorkout
import project.st991570719.rob.st991444103.sean.database.lifting.LiftingWorkoutDao


//, exportSchema = false --> do we need this??
@Database(entities = [CardioWorkout::class, LiftingWorkout::class],  version = 1, exportSchema = false)
abstract class WorkoutDatabase: RoomDatabase() {

    abstract fun cardioWorkoutDao(): CardioWorkoutDao
    abstract fun liftingWorkoutDao(): LiftingWorkoutDao

    companion object {

        @Volatile
        private var INSTANCE: WorkoutDatabase? = null

        fun getInstance(context: Context): WorkoutDatabase {
            synchronized(this) {
//                var instance = INSTANCE
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WorkoutDatabase::class.java,
                        "workout_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
//                    INSTANCE = instance
                }
                return INSTANCE as WorkoutDatabase
            }
        }
    }
}