package project.st991570719.rob.st991444103.sean.database.lifting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface LiftingWorkoutDao {

    @Insert
    fun insert(lifts: LiftingWorkout)

    @Update
    fun update(lifts: LiftingWorkout)

    @Delete
    fun delete(lifts: LiftingWorkout)

    @Query("DELETE FROM lifting_workout")
    suspend fun deleteAllLifting()

    @Query("SELECT * FROM lifting_workout ORDER BY date DESC")
//    fun getAllLifts(): MutableLiveData<List<LiftingWorkout>>
    fun getAllLifts(): LiveData<List<LiftingWorkout>>

}