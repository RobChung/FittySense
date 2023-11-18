package project.st991570719.rob.st991444103.sean.diettracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import project.st991570719.rob.st991444103.sean.database.FoodConsumptionDatabase
import project.st991570719.rob.st991444103.sean.database.foodconsumed.FoodConsumption
import project.st991570719.rob.st991444103.sean.databinding.FragmentAddFoodBinding

class AddFoodFragment: Fragment() {

    private lateinit var binding: FragmentAddFoodBinding
    private lateinit var foodDB: FoodConsumptionDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentAddFoodBinding.inflate(layoutInflater)
        foodDB = activity?.let { FoodConsumptionDatabase.getInstance(it.applicationContext) }!!

        binding.enterFoodBtn.setOnClickListener() {
            val foodItem = binding.foodItemInput.text.toString()
            val calories = binding.caloriesInput.text.toString().toInt()
            val servings = binding.servingsInput.text.toString().toInt()
            val fItem = FoodConsumption(
                0,
                foodItem,
                servings,
                calories
            )

            binding.foodItemInput.setText("")
            binding.servingsInput.setText("")
            binding.caloriesInput.setText("")
            addFoodToDB(fItem)
            Toast.makeText(context, "Added Food", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }
    private fun addFoodToDB(foodItem: FoodConsumption) {
        CoroutineScope(Dispatchers.IO).launch {
            foodDB.foodConsumptionDao().insert(foodItem)
        }
    }
}


