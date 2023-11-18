package project.st991570719.rob.st991444103.sean.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import project.st991570719.rob.st991444103.sean.database.foodconsumed.FoodConsumption
import project.st991570719.rob.st991444103.sean.database.foodconsumed.FoodConsumptionDao


//, exportSchema = false --> do we need this??
@Database(entities = [FoodConsumption::class], version = 1)
abstract class FoodConsumptionDatabase: RoomDatabase() {

    abstract fun foodConsumptionDao(): FoodConsumptionDao

    companion object {

        @Volatile
        private var INSTANCE: FoodConsumptionDatabase? = null

        fun getInstance(context: Context): FoodConsumptionDatabase {
//            synchronized(this) {
//                var instance = INSTANCE
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    FoodConsumptionDatabase::class.java,
                    "foodconsumption_database"
                )
//                        .fallbackToDestructiveMigration()
                    .build()
//                    INSTANCE = instance
            }
            return INSTANCE as FoodConsumptionDatabase
        }
    }
}
