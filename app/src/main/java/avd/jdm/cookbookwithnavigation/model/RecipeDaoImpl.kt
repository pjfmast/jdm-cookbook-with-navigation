package avd.jdm.cookbookwithnavigation.model

class RecipeDaoImpl : RecipeDao {
    override fun getAll(): List<Recipe> {
        return listOf(
            Recipe(
                "Lasagne",
                "Fry onion. Add meat and garlic and fry. " +
                        "Add tomatoes and sugar and simmer 20 minutes. " +
                        "Pre-heat oven. Prepare béchamel sauce. " +
                        "Create alternating layers of lasagna sheets and sauce. " +
                        "Cook in oven for 45 minutes.",
                RecipeType.MEAT,
                nutritionalValue = NutritionalValue(197, 13, 10, 14)
            ),
            Recipe(
                "Tuna Salad",
                "Slice tomato, onion and tuna. " + "Mix ingredients in a bowl. Chill before serving.",
                RecipeType.FISH,
                nutritionalValue = NutritionalValue(191, 3, 15, 11)
            ),
            Recipe(
                "Calzone",
                "Roll pizza dough. Spoon cheese mixture into the center. " + "Fold dough over. Seal edges with fork. Bake 12 minutes.",
                RecipeType.VEGAN,
                nutritionalValue = NutritionalValue(385, 29, 22, 17)
            )
        )
    }

    override fun find(title: String): Recipe? =
        getAll().firstOrNull() { it.title == title }

}
