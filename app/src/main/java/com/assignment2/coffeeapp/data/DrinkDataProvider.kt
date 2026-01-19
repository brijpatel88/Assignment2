package com.assignment2.coffeeapp.data

import com.assignment2.coffeeapp.R

object DrinkDataProvider {

    val allDrinks: List<Drink> = listOf(

        // ---------------- COFFEE ----------------
        Drink(1, "House Coffee", R.drawable.coffee, "Fresh brewed, smooth and warm", 2.00, DrinkType.COFFEE, DrinkSize.values().toList()),
        Drink(4, "Espresso", R.drawable.coffee, "Strong single shot espresso", 2.25, DrinkType.COFFEE, DrinkSize.values().toList()),
        Drink(5, "Americano", R.drawable.coffee, "Espresso + hot water, bold taste", 2.75, DrinkType.COFFEE, DrinkSize.values().toList()),
        Drink(6, "Cappuccino", R.drawable.coffee, "Espresso with foam and steamed milk", 3.25, DrinkType.COFFEE, DrinkSize.values().toList()),
        Drink(7, "Mocha", R.drawable.coffee, "Espresso with chocolate and milk", 3.75, DrinkType.COFFEE, DrinkSize.values().toList()),

        // ---------------- LATTE ----------------
        Drink(2, "Classic Latte", R.drawable.latte, "Espresso with steamed milk", 3.50, DrinkType.LATTE, DrinkSize.values().toList()),
        Drink(8, "Vanilla Latte", R.drawable.latte, "Vanilla syrup with smooth foam", 4.00, DrinkType.LATTE, DrinkSize.values().toList()),
        Drink(9, "Caramel Latte", R.drawable.latte, "Sweet caramel café favorite", 4.25, DrinkType.LATTE, DrinkSize.values().toList()),
        Drink(10, "Hazelnut Latte", R.drawable.latte, "Nutty and smooth flavor", 4.25, DrinkType.LATTE, DrinkSize.values().toList()),

        // ---------------- TEA ----------------
        Drink(3, "Classic Tea", R.drawable.tea, "Hot steeped tea", 1.75, DrinkType.TEA, DrinkSize.values().toList()),
        Drink(11, "Green Tea", R.drawable.tea, "Light, fresh, and calming", 2.00, DrinkType.TEA, DrinkSize.values().toList()),
        Drink(12, "Chai Tea", R.drawable.tea, "Spiced tea with warm flavor", 2.50, DrinkType.TEA, DrinkSize.values().toList()),
        Drink(13, "Earl Grey", R.drawable.tea, "Black tea with bergamot aroma", 2.25, DrinkType.TEA, DrinkSize.values().toList()),
        Drink(14, "Herbal Tea", R.drawable.tea, "Caffeine-free relaxing blend", 2.25, DrinkType.TEA, DrinkSize.values().toList())
    )

    fun drinksForCategory(category: String): List<Drink> {
        // category passed as "COFFEE" / "LATTE" / "TEA"
        return allDrinks.filter { it.type.name == category }
    }

    fun getById(id: Int): Drink? = allDrinks.firstOrNull { it.id == id }
}