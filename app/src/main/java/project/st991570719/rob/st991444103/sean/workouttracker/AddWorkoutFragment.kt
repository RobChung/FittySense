package project.st991570719.rob.st991444103.sean.workouttracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import project.st991570719.rob.st991444103.sean.database.WorkoutDatabase
import project.st991570719.rob.st991444103.sean.database.cardio.CardioWorkout
import project.st991570719.rob.st991444103.sean.database.lifting.LiftingWorkout
import project.st991570719.rob.st991444103.sean.databinding.FragmentAddWorkoutBinding

class AddWorkoutFragment : Fragment() {

    private lateinit var binding: FragmentAddWorkoutBinding
    private lateinit var workoutDB: WorkoutDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Variable to check which workout to add to the appropriate DB
        var isCardio = false

        binding = FragmentAddWorkoutBinding.inflate(layoutInflater)
        workoutDB = activity?.let { WorkoutDatabase.getInstance(it.applicationContext) }!!


        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == binding.radioButtonCardio.id) {
                binding.inputReps.isEnabled = false
                binding.inputSets.isEnabled = false
                binding.inputLbs.isEnabled = false

                binding.inputDistance.isEnabled = true
                isCardio = true
            } else {
                binding.inputReps.isEnabled = true
                binding.inputSets.isEnabled = true
                binding.inputLbs.isEnabled = true

                binding.inputDistance.isEnabled = false
                isCardio = false

            }
        }

        binding.buttonAddWorkout.setOnClickListener() {
            val workoutName = binding.nameInput.text.toString()
            val year = binding.simpleDatePicker.year.toString()
            val month = binding.simpleDatePicker.month.toString()
            val day = binding.simpleDatePicker.dayOfMonth.toString()
            val dateString = "$year/$month/$day"
            if (isCardio) {
                val distance = binding.inputDistance.text.toString().toDouble()

                val cardioWorkout = CardioWorkout(
                    0,
                    dateString,
                    workoutName,
                    distance
                )
                addCardioToDB(cardioWorkout)
                Toast.makeText(context, "Added $workoutName", Toast.LENGTH_SHORT).show()

            } else {
                // is a lifting exercise
                val sets = binding.inputSets.text.toString().toInt()
                val reps = binding.inputReps.text.toString().toInt()
                val weight = binding.inputLbs.text.toString().toDouble()

                val liftingWorkout = LiftingWorkout(
                    0,
                    dateString,
                    workoutName,
                    sets,
                    reps,
                    weight
                )
                addLiftingToDB(liftingWorkout)
                Toast.makeText(context, "Added $workoutName", Toast.LENGTH_SHORT).show()

            }

            // Clear inputs
            binding.nameInput.setText("")
            binding.inputDistance.setText("")
            binding.inputSets.setText("")
            binding.inputReps.setText("")
            binding.inputLbs.setText("")

        }

        return binding.root
    }

    private fun addCardioToDB(cardioWorkout: CardioWorkout) {
        CoroutineScope(Dispatchers.IO).launch {
            workoutDB.cardioWorkoutDao().insert(cardioWorkout)
        }
    }

    private fun addLiftingToDB(liftingWorkout: LiftingWorkout) {
        CoroutineScope(Dispatchers.IO).launch {
            workoutDB.liftingWorkoutDao().insert(liftingWorkout)
        }
    }
//    private fun populateWorkoutSpinner(context: Context) {
//        // Put spinner population in separate function call
//        val workoutTypes = arrayOf(
//            "Cardio",
//            "Lifting"
//        )
//        val workoutArrayAdapter = ArrayAdapter(
//            context, android.R.layout.simple_spinner_item, workoutTypes
//        )
//
//        workoutType = binding.workoutSpinner
//        workoutType.adapter = workoutArrayAdapter
//    }
}