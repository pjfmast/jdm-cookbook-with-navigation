package avd.jdm.cookbookwithnavigation.model

interface RecipeDao {
    fun getAll(): List<Recipe>
    fun find(title: String): Recipe?
}
