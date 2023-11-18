package project.st991570719.rob.st991444103.sean.database.foodconsumed

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FoodConsumptionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(food: FoodConsumption)

    @Update
    fun update(food: FoodConsumption)

    @Delete
    fun delete(food: FoodConsumption)

    @Query("SELECT * FROM food_consumption")
    fun getAllFood(): LiveData<List<FoodConsumption>>

    @Query("DELETE FROM food_consumption")
    suspend fun deleteAllFood()
}