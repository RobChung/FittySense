package project.st991570719.rob.st991444103.sean.workouttracker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardio_list_item.view.*
import project.st991570719.rob.st991444103.sean.R
import project.st991570719.rob.st991444103.sean.database.cardio.CardioWorkout
import project.st991570719.rob.st991444103.sean.database.lifting.LiftingWorkout

// can we pass both lists?
class WorkoutRecyclerView (
//    private var cardioList: List<CardioWorkout>,
//    private var liftingList: List<LiftingWorkout>,
    private var workoutList: List<Any>
    ): RecyclerView.Adapter <WorkoutRecyclerView.MyViewHolder>() {

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.workoutName
        val date: TextView = itemView.workoutDate
        val text1: TextView = itemView.textView1
        val text2: TextView = itemView.textView2
        val text3: TextView = itemView.textView3


    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCardioData(cardioList: ArrayList<CardioWorkout>) {
        this.workoutList += cardioList
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setLiftingData(liftingList: ArrayList<LiftingWorkout>) {
        this.workoutList += liftingList
        notifyDataSetChanged()
    }

    fun clearData() {
        this.workoutList = listOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.cardio_list_item,
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = workoutList[position]
        if (currentItem is CardioWorkout) {
            holder.name.text = currentItem.cardioName
            holder.date.text = currentItem.date
            holder.text3.text = currentItem.distance.toString() + " KM"
            holder.text1.text = ""
            holder.text2.text = ""

        }
        else if (currentItem is LiftingWorkout) {
            holder.name.text = currentItem.liftingName
            holder.date.text = currentItem.date
            holder.text1.text = currentItem.sets.toString() + " SETS"
            holder.text2.text = currentItem.reps.toString() + " REPS"
            holder.text3.text = currentItem.weight.toString() + " LBS"
        }
    }

    override fun getItemCount() = workoutList.size


}