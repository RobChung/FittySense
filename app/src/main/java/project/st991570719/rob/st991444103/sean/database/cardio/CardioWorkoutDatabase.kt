package project.st991570719.rob.st991444103.sean.database.cardio

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//, exportSchema = false --> do we need this??
@Database(entities = [CardioWorkout::class], version = 1)
abstract class CardioDatabase: RoomDatabase() {

    abstract val cardioWorkoutDao: CardioWorkoutDao

    companion object {

        @Volatile
        private var INSTANCE: CardioDatabase? = null

        fun getInstance(context: Context): CardioDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CardioDatabase::class.java,
                        "cardio_database"
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