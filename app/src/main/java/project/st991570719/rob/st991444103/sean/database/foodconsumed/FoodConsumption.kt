package project.st991570719.rob.st991444103.sean.database.foodconsumed

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_consumption")
data class FoodConsumption (
    @PrimaryKey(autoGenerate = true)
    var foodId: Long = 0L,

    @ColumnInfo(name = "food_item")
    val foodItem: String,

    @ColumnInfo(name = "servings")
    val servings: Int,

    @ColumnInfo(name = "calories")
    val calories: Int

)