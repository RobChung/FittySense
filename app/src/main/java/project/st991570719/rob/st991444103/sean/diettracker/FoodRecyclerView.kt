package project.st991570719.rob.st991444103.sean.diettracker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardio_list_item.view.*
import kotlinx.android.synthetic.main.food_list_item.view.*
import project.st991570719.rob.st991444103.sean.R
import project.st991570719.rob.st991444103.sean.database.foodconsumed.FoodConsumption

class FoodRecyclerView(private var foodList: List<FoodConsumption>): RecyclerView.Adapter <FoodRecyclerView.MyViewHolder>(){

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val foodItem: TextView = itemView.foodItem
        val servings: TextView = itemView.servings
        val calories: TextView = itemView.calories

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFoodData(foodList: ArrayList<FoodConsumption>) {
        this.foodList = foodList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.food_list_item,
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = foodList[position]
        holder.foodItem.text = currentItem.foodItem
        holder.servings.text = currentItem.servings.toString()
        holder.calories.text = currentItem.calories.toString()
    }

    override fun getItemCount() = foodList.size
}
