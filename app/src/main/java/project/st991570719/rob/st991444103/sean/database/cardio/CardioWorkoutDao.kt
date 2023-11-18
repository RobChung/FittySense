package project.st991570719.rob.st991444103.sean.database.cardio

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CardioWorkoutDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cardio: CardioWorkout)

    @Update
    fun update(cardio: CardioWorkout)

    @Delete
    fun delete(cardio: CardioWorkout)

    @Query("DELETE FROM cardio_workout")
    suspend fun deleteAllCardio()

    @Query("SELECT * FROM cardio_workout")
    fun getAllCardio(): LiveData<List<CardioWorkout>>
}