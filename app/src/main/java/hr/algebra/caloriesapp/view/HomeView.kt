package hr.algebra.caloriesapp.view

import hr.algebra.caloriesapp.model.Food

interface HomeView {
    fun displayFoodList(foodList: List<Food>)
}
