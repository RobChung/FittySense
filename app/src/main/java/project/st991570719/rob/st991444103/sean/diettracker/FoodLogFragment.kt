package project.st991570719.rob.st991444103.sean.diettracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import project.st991570719.rob.st991444103.sean.R
import project.st991570719.rob.st991444103.sean.database.FoodConsumptionDatabase
import project.st991570719.rob.st991444103.sean.database.foodconsumed.FoodConsumption
import project.st991570719.rob.st991444103.sean.databinding.FragmentFoodLogBinding

class FoodLogFragment: Fragment() {
    private lateinit var foodDB: FoodConsumptionDatabase
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentFoodLogBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_food_log, container, false)
        binding.lifecycleOwner = this
        foodDB = activity?.let { FoodConsumptionDatabase.getInstance(it.applicationContext) }!!
        val application = requireNotNull(this.activity).application

        var foodList = ArrayList<FoodConsumption>()

        var myAdapter = FoodRecyclerView(foodList)
        binding.recyclerView2.adapter = myAdapter
        binding.recyclerView2.layoutManager = LinearLayoutManager(requireActivity())

        val viewModelFactory = FoodViewModelFactory(foodDB.foodConsumptionDao(), application)
        var foodViewModel = ViewModelProvider(
            this, viewModelFactory).get(FoodViewModel::class.java)

        var totalCalsLeft = binding.calRemaining.text.toString().toInt()
        var caloriesToSubtract = 0
        foodViewModel.getAllFoodFromDatabase().observe(viewLifecycleOwner) {
            myAdapter.setFoodData(it as ArrayList<FoodConsumption>)
//            Log.d("IT IS: ", it.toString())
            for (food in it) {
//                Log.d("FOOD IS ", food.toString())
//                Log.d("CALORIES IS ", food.calories.toString())
                caloriesToSubtract += food.calories
                binding.calRemaining.text = (totalCalsLeft - caloriesToSubtract).toString()
            }
        }

        binding.addFoodBtn.setOnClickListener() {
            view?.findNavController()?.navigate(R.id.action_menuFragment_to_addFoodFragment)
        }

        binding.deleteAllBtn.setOnClickListener(){
            uiScope.launch {
                foodViewModel.deleteAllFromDatabase()
                binding.calRemaining.text = "2000"
            }
        }

        return binding.root
    }
}