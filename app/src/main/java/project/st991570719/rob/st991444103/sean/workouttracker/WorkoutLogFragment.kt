package project.st991570719.rob.st991444103.sean.workouttracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import project.st991570719.rob.st991444103.sean.R
import project.st991570719.rob.st991444103.sean.database.WorkoutDatabase
import project.st991570719.rob.st991444103.sean.databinding.FragmentWorkoutLogBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import project.st991570719.rob.st991444103.sean.database.cardio.CardioWorkout
import project.st991570719.rob.st991444103.sean.database.lifting.LiftingWorkout

class WorkoutLogFragment : Fragment() {

    private lateinit var workoutDB: WorkoutDatabase
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentWorkoutLogBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_workout_log, container, false)
        binding.lifecycleOwner = this
        workoutDB = activity?.let { WorkoutDatabase.getInstance(it.applicationContext) }!!
        val application = requireNotNull(this.activity).application
        var workoutList = ArrayList<Any>()

        var myAdapter = WorkoutRecyclerView(workoutList)
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        val viewModelFactory = CardioViewModelFactory(workoutDB.cardioWorkoutDao(), workoutDB.liftingWorkoutDao(), application)
        var cardioViewModel = ViewModelProvider(
            this, viewModelFactory).get(CardioViewModel::class.java)

        cardioViewModel.getAllCardioFromDatabase().observe(viewLifecycleOwner) {
            myAdapter.setCardioData(it as ArrayList<CardioWorkout>)
        }

        cardioViewModel.getAllLiftsFromDatabase().observe(viewLifecycleOwner) {
            myAdapter.setLiftingData(it as ArrayList<LiftingWorkout>)
        }

        // Button to move to AddWorkout page
        binding.buttonToAddView.setOnClickListener() {
            view?.findNavController()?.navigate(R.id.action_workoutLogFragment_to_addWorkoutFragment)
        }

        binding.exRecoBtn.setOnClickListener() {
            view?.findNavController()?.navigate(R.id.action_workoutLogFragment_to_WorkoutWebFragment)
        }

        binding.clearBtn.setOnClickListener() {
            uiScope.launch {
                cardioViewModel.deleteAll()
                myAdapter.clearData()
            }
        }

        return binding.root
    }
}