package project.st991570719.rob.st991444103.sean.diettracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import project.st991570719.rob.st991444103.sean.database.foodconsumed.FoodConsumption
import project.st991570719.rob.st991444103.sean.database.foodconsumed.FoodConsumptionDao

class FoodViewModel(
    private val foodDatabase: FoodConsumptionDao,
    application: Application): AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var foodList = foodDatabase.getAllFood()

    init {
        initializeFoodList()
    }

    private fun initializeFoodList() {
        uiScope.launch {
            foodList = getAllFoodFromDatabase()
        }
    }

    fun getAllFoodFromDatabase(): LiveData<List<FoodConsumption>> {
        foodList = foodDatabase.getAllFood()

        return foodList
    }

     suspend fun deleteAllFromDatabase() {
         foodDatabase.deleteAllFood()
    }
}