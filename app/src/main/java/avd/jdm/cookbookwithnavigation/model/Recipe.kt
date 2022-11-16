package avd.jdm.cookbookwithnavigation.model

data class Recipe(
    val title: String,
    val description: String,
    val recipeType: RecipeType,
    val nutritionalValue: NutritionalValue
)

enum class RecipeType {
    MEAT, FISH, VEGAN
}

data class NutritionalValue(
    val calories: Int,
    val carbohydrates: Int,
    val fat: Int,
    val protein: Int
)
