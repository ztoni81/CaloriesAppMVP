package hr.algebra.caloriesapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import hr.algebra.caloriesapp.model.Food

@Dao
interface FoodDao {

    @Query("SELECT * FROM Food")
    fun getAllFoodItems(): List<Food>

    @Insert
    fun insertFoodItem(food: Food)

    @Delete
    fun deleteFoodItem(food: Food)
}