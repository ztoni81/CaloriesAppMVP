package hr.algebra.caloriesapp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.caloriesapp.R
import hr.algebra.caloriesapp.dao.AppDatabase
import hr.algebra.caloriesapp.model.Food
import hr.algebra.caloriesapp.presenter.HomePresenter
import hr.algebra.caloriesapp.repository.FoodRepository


class HomeActivity : AppCompatActivity(), HomeView {
    private lateinit var rvFoodItems: RecyclerView
    private lateinit var etFoodName: EditText
    private lateinit var etCalorieCount: EditText
    private lateinit var btnSave: Button



    private lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        rvFoodItems = findViewById(R.id.rvFoodItemList)
        rvFoodItems.layoutManager = LinearLayoutManager(this)
        etFoodName = findViewById(R.id.etFoodName)
        etCalorieCount = findViewById(R.id.etCalorieCount)
        btnSave = findViewById(R.id.btnSave)


        val foodDao = AppDatabase.getInstance(applicationContext).foodDao()
        val foodRepository = FoodRepository(foodDao)

        homePresenter = HomePresenter(this, foodRepository)
        homePresenter.loadFoodList()

        btnSave.setOnClickListener {
            if (isInputValid()) {
                homePresenter.addFoodItemToFoodList(
                    etFoodName.text.toString(),
                    etCalorieCount.text.toString()
                )
                etFoodName.text.clear()
                etCalorieCount.text.clear()
            }
        }

        val divider = DividerItemDecoration(rvFoodItems.context, LinearLayoutManager.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.divider)?.let{ it->divider.setDrawable(it)}
        rvFoodItems.addItemDecoration(divider)
    }

    override fun displayFoodList(foodList: List<Food>) {
        val adapter = FoodAdapter(this, foodList, homePresenter)
        rvFoodItems.adapter = adapter
    }

    private fun isInputValid(): Boolean {
        if (etFoodName.text.trim().isEmpty()) {
            etFoodName.error = "Please enter Food name..."
            return false
        }
        if (etCalorieCount.text.trim().isEmpty() || !etCalorieCount.text.trim().isDigitsOnly() || etCalorieCount.text.trim().toString().toLong() !in 0..1000) {
            etCalorieCount.error = "Please enter a number between 0 and 1000..."
            return false
        }
        return true
    }
}