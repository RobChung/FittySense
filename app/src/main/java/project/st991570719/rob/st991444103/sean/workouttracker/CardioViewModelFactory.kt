package project.st991570719.rob.st991444103.sean.workouttracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import project.st991570719.rob.st991444103.sean.database.cardio.CardioWorkoutDao
import project.st991570719.rob.st991444103.sean.database.lifting.LiftingWorkoutDao

class CardioViewModelFactory(
    private val cardioDataSource: CardioWorkoutDao,
    private val liftingDataSource: LiftingWorkoutDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardioViewModel::class.java)) {
            return CardioViewModel(cardioDataSource, liftingDataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
