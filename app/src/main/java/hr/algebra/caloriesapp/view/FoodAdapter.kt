package hr.algebra.caloriesapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.caloriesapp.R
import hr.algebra.caloriesapp.model.Food
import hr.algebra.caloriesapp.presenter.HomePresenter

class FoodAdapter(
    private val context: Context,
    private val foodList: List<Food>,
    private val homePresenter: HomePresenter
) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val foodItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_item, parent, false)
        return ViewHolder(foodItemView)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, index: Int) {
        holder.tvFoodName.text = foodList[index].name
        holder.tvCalories.text = foodList[index].calorieCount
        holder.clFoodItem.setOnClickListener {
            val deleteDialogBuilder = AlertDialog.Builder(context)
            deleteDialogBuilder.setMessage("Are you sure you want to delete item \"${holder.tvFoodName.text}\"?")
            deleteDialogBuilder.setPositiveButton("YES") { dialog, which ->
                homePresenter.deleteFoodItem(foodList[index])
                Toast.makeText(context, "\"${holder.tvFoodName.text}\" as been deleted!", Toast.LENGTH_SHORT).show()
                true
            }
            deleteDialogBuilder.setNegativeButton("NO") { dialog, which ->
            }
            val deleteDialog = deleteDialogBuilder.create()
            deleteDialog.show()
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFoodName: TextView = itemView.findViewById(R.id.tvFoodName)
        val tvCalories: TextView = itemView.findViewById(R.id.tvCalorieCount)
        val clFoodItem: ConstraintLayout = itemView.findViewById(R.id.clFoodItem)
    }
}