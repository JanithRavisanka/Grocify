package com.example.grocify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "HomeActivity"
    }

    private lateinit var categoryFilters: List<TextView>
    private lateinit var groceryItemsContainer: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Starting HomeActivity")
        setContentView(R.layout.activity_home)

        setupCategoryFilters()
        setupBottomNavigation()
        Log.d(TAG, "onCreate: Setup complete")
    }

    private fun setupCategoryFilters() {
        Log.d(TAG, "setupCategoryFilters: Setting up category filters")
        
        // Initialize category filter views
        categoryFilters = listOf(
            findViewById(R.id.category_filter_all),
            findViewById(R.id.category_filter_vegetables),
            findViewById(R.id.category_filter_fruits),
            findViewById(R.id.category_filter_dairy),
            findViewById(R.id.category_filter_bakery),
            findViewById(R.id.category_filter_meat),
            findViewById(R.id.category_filter_snacks)
        )

        // Set up click listeners for each filter
        categoryFilters.forEachIndexed { index, filter ->
            filter.setOnClickListener {
                Log.d(TAG, "Category filter clicked: ${filter.text}")
                selectCategory(index, filter.text.toString())
            }
        }

        // Set initial selection
        selectCategory(0, "all")
        Log.d(TAG, "setupCategoryFilters: Category filters setup complete")
    }

    private fun selectCategory(index: Int, category: String) {
        Log.d(TAG, "selectCategory: Selecting category: $category")
        
        // Update visual selection
        categoryFilters.forEachIndexed { i, filter ->
            if (i == index) {
                filter.setBackgroundResource(R.drawable.bg_chip_selected)
                filter.setTextColor(resources.getColor(R.color.text_on_primary, null))
            } else {
                filter.setBackgroundResource(R.drawable.bg_chip_unselected)
                filter.setTextColor(resources.getColor(R.color.text_secondary, null))
            }
        }

        // Update grocery items visibility based on category
        updateGroceryItems(category.lowercase())
    }

    private fun updateGroceryItems(category: String) {
        Log.d(TAG, "updateGroceryItems: Updating items for category: $category")
        
        // Get the grocery items container
        groceryItemsContainer = findViewById(R.id.grocery_grid)
        
        // Show/hide appropriate grocery items based on selected category
        when (category) {
            "all" -> showAllGroceryItems()
            "vegetables" -> showVegetableItems()
            "fruits" -> showFruitItems()
            "dairy" -> showDairyItems()
            "bakery" -> showBakeryItems()
            "meat" -> showMeatItems()
            "snacks" -> showSnackItems()
            else -> showAllGroceryItems()
        }
    }

    private fun showAllGroceryItems() {
        Log.d(TAG, "showAllGroceryItems: Showing all grocery items")
        // Show all items by making the container visible
        groceryItemsContainer.visibility = View.VISIBLE
    }

    private fun showVegetableItems() {
        Log.d(TAG, "showVegetableItems: Showing vegetable items")
        // Show only vegetable items
        showCategoryItems(listOf("carrot", "tomato", "onion", "potato"))
    }

    private fun showFruitItems() {
        Log.d(TAG, "showFruitItems: Showing fruit items")
        // Show only fruit items
        showCategoryItems(listOf("apple", "banana", "orange", "strawberry"))
    }

    private fun showDairyItems() {
        Log.d(TAG, "showDairyItems: Showing dairy items")
        // Show only dairy items
        showCategoryItems(listOf("milk", "cheese", "yogurt", "butter"))
    }

    private fun showBakeryItems() {
        Log.d(TAG, "showBakeryItems: Showing bakery items")
        // Show only bakery items
        showCategoryItems(listOf("bread", "cake", "croissant", "muffin"))
    }

    private fun showMeatItems() {
        Log.d(TAG, "showMeatItems: Showing meat items")
        // Show only meat items
        showCategoryItems(listOf("chicken", "beef", "pork", "fish"))
    }

    private fun showSnackItems() {
        Log.d(TAG, "showSnackItems: Showing snack items")
        // Show only snack items
        showCategoryItems(listOf("chips", "cookies", "nuts", "crackers"))
    }

    private fun showCategoryItems(itemNames: List<String>) {
        Log.d(TAG, "showCategoryItems: Showing items: $itemNames")
        
        // For now, we'll just log the filtering
        // In a real implementation, this would update the RecyclerView or GridLayout
        // Since we're using static XML, we'll show all items but log the filter
        groceryItemsContainer.visibility = View.VISIBLE
        
        // Log which items should be visible for this category
        itemNames.forEach { itemName ->
            Log.d(TAG, "Category filter: Should show $itemName")
        }
    }

    private fun setupBottomNavigation() {
        Log.d(TAG, "setupBottomNavigation: Starting setup")
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        if (bottomNav != null) {
            Log.d(TAG, "setupBottomNavigation: Bottom navigation found")
            bottomNav.selectedItemId = R.id.nav_groceries
            Log.d(TAG, "setupBottomNavigation: Selected item set to nav_groceries")
            
            bottomNav.setOnItemSelectedListener { menuItem ->
                Log.d(TAG, "setupBottomNavigation: Item selected: ${menuItem.itemId}")
                when (menuItem.itemId) {
                    R.id.nav_groceries -> {
                        Log.d(TAG, "setupBottomNavigation: Already on groceries page")
                        true
                    }
                    R.id.nav_lists -> {
                        Log.d(TAG, "setupBottomNavigation: Navigating to ListsActivity")
                        val intent = Intent(this, ListsActivity::class.java)
                        startActivity(intent)
                        finish()
                        true
                    }
                    R.id.nav_profile -> {
                        Log.d(TAG, "setupBottomNavigation: Navigating to ProfileActivity")
                        val intent = Intent(this, ProfileActivity::class.java)
                        startActivity(intent)
                        finish()
                        true
                    }
                    R.id.nav_settings -> {
                        Log.d(TAG, "setupBottomNavigation: Navigating to SettingsActivity")
                        val intent = Intent(this, SettingsActivity::class.java)
                        startActivity(intent)
                        finish()
                        true
                    }
                    else -> {
                        Log.d(TAG, "setupBottomNavigation: Unknown item selected: ${menuItem.itemId}")
                        true
                    }
                }
            }
            Log.d(TAG, "setupBottomNavigation: Navigation listener set")
        } else {
            Log.e(TAG, "setupBottomNavigation: Bottom navigation not found!")
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: HomeActivity resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: HomeActivity paused")
    }
}
