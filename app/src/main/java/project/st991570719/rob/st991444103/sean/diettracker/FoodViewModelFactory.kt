package project.st991570719.rob.st991444103.sean.diettracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import project.st991570719.rob.st991444103.sean.database.foodconsumed.FoodConsumptionDao

class FoodViewModelFactory (
    private val foodDataSource: FoodConsumptionDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            return FoodViewModel(foodDataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}