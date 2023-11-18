package project.st991570719.rob.st991444103.sean.database.lifting

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//, exportSchema = false --> do we need this??
@Database(entities = [LiftingWorkout::class], version = 1)
abstract class LiftingDatabase: RoomDatabase() {

    abstract val liftingWorkoutDao: LiftingWorkoutDao

    companion object {

        @Volatile
        private var INSTANCE: LiftingDatabase? = null

        fun getInstance(context: Context): LiftingDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LiftingDatabase::class.java,
                        "lifting_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}