package hr.algebra.caloriesapp.repository

import hr.algebra.caloriesapp.dao.FoodDao
import hr.algebra.caloriesapp.model.Food

class FoodRepository(private val foodDao: FoodDao) {

    fun getAll(): List<Food> {
        return foodDao.getAllFoodItems()
    }

    fun insert(foodItem: Food) {
        foodDao.insertFoodItem(foodItem)
    }

    fun delete(foodItem: Food) {
        foodDao.deleteFoodItem(foodItem)
    }
}