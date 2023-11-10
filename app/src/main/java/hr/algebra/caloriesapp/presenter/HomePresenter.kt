package hr.algebra.caloriesapp.presenter

import hr.algebra.caloriesapp.model.Food
import hr.algebra.caloriesapp.repository.FoodRepository
import hr.algebra.caloriesapp.view.HomeView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePresenter(
    private val homeView: HomeView,
    private val foodRepository: FoodRepository
) {
    fun loadFoodList() {
        Thread {
            val foodList = foodRepository.getAll()
            homeView.displayFoodList(foodList)
        }.start()
    }

    fun addFoodItemToFoodList(name: String, calorieCount: String) {
        val newFoodItem = Food(name = name, calorieCount = calorieCount)
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                foodRepository.insert(newFoodItem)
                val foodList = foodRepository.getAll()
                withContext(Dispatchers.Main) {
                    homeView.displayFoodList(foodList)
                }
            }
        }
    }

    fun deleteFoodItem(food: Food) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                foodRepository.delete(food)
                val foodList = foodRepository.getAll()
                withContext(Dispatchers.Main) {
                    homeView.displayFoodList(foodList)
                }
            }
        }
    }
}