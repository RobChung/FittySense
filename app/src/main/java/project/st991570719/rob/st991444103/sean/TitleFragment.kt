package project.st991570719.rob.st991444103.sean

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import project.st991570719.rob.st991444103.sean.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

        //private lateinit var binding: FragmentTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false)

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_title, container, false)

        binding.buttonBegin.setOnClickListener() {
            view?.findNavController()?.navigate(R.id.action_titleFragment_to_menuFragment)
        }

        return binding.root
    }

}