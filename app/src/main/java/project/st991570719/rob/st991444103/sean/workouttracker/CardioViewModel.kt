package project.st991570719.rob.st991444103.sean.workouttracker;

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import project.st991570719.rob.st991444103.sean.database.cardio.CardioWorkout
import project.st991570719.rob.st991444103.sean.database.cardio.CardioWorkoutDao
import project.st991570719.rob.st991444103.sean.database.lifting.LiftingWorkout
import project.st991570719.rob.st991444103.sean.database.lifting.LiftingWorkoutDao

class CardioViewModel(
    private val cardioDatabase: CardioWorkoutDao,
    private val liftingDatabase: LiftingWorkoutDao,
    application: Application
) :AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var liftingWorkoutList = liftingDatabase.getAllLifts()
    private var cardioWorkoutList = cardioDatabase.getAllCardio()
//    private var liftingWorkoutList = MutableLiveData<List<LiftingWorkout>>()


    init {
        initializeWorkoutList()
    }

     private fun initializeWorkoutList() {
        uiScope.launch {
            cardioWorkoutList = getAllCardioFromDatabase()
            liftingWorkoutList = getAllLiftsFromDatabase()
        }
     }

    fun getAllCardioFromDatabase(): LiveData<List<CardioWorkout>> {
    cardioWorkoutList = cardioDatabase.getAllCardio()

    return cardioWorkoutList
    }

    fun getAllLiftsFromDatabase() : LiveData<List<LiftingWorkout>> {
        liftingWorkoutList = liftingDatabase.getAllLifts()

        return liftingWorkoutList
    }

    suspend fun deleteAll() {
        uiScope.launch {
            cardioDatabase.deleteAllCardio()
            liftingDatabase.deleteAllLifting()
        }
    }
}
