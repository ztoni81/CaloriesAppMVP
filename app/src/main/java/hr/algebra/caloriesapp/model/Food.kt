package hr.algebra.caloriesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val calorieCount: String
)