package project.st991570719.rob.st991444103.sean

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import project.st991570719.rob.st991444103.sean.databinding.FragmentMenuBinding
import project.st991570719.rob.st991444103.sean.R


class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_menu, container, false)
        val binding: FragmentMenuBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_menu, container, false)

        binding.buttonWorkout.setOnClickListener() {
            view?.findNavController()?.navigate(R.id.action_menuFragment_to_workoutLogFragment)
        }

        binding.buttonDiet.setOnClickListener() {
            view?.findNavController()?.navigate(R.id.action_menuFragment_to_FoodLogFragment)
        }

        return binding.root
    }
}