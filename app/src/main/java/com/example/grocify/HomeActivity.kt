package com.example.grocify

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.widget.FrameLayout
import android.view.View
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    
    private lateinit var groceryGrid: GridLayout
    private val categoryFilters = mutableListOf<TextView>()
    private val selectedCategories = mutableSetOf<String>()
    private val shoppingList = mutableMapOf<String, Int>() // Product name to quantity mapping
    
    private lateinit var cartBadge: TextView
    
    // Dummy data for each category
    private val categoryProducts = mapOf(
        "All" to listOf("Carrot", "Apple", "Milk", "Bread", "Chicken", "Chips"),
        "Vegetables" to listOf("Carrot", "Potato", "Beetroot", "Pumpkin", "Cabbage", "Tomato"),
        "Fruits" to listOf("Apple", "Orange", "Banana", "Grapes", "Watermelon", "Strawberry"),
        "Dairy" to listOf("Milk", "Cheese", "Yogurt", "Butter", "Cream", "Ice Cream"),
        "Bakery" to listOf("Bread", "Croissant", "Muffin", "Cake", "Cookies", "Bagel"),
        "Meat" to listOf("Chicken", "Beef", "Fish", "Pork", "Turkey", "Lamb"),
        "Snacks" to listOf("Chips", "Nuts", "Crackers", "Popcorn", "Pretzels", "Candy")
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        
        groceryGrid = findViewById(R.id.grocery_grid)
        cartBadge = findViewById(R.id.cart_badge)

        // Initialize with "All" selected
        selectedCategories.add("All")
        
        setupCategoryFilters()
        setupGroceryGrid()
        setupShoppingCartClick()
        setupBottomNavigation()
        updateProductsForSelectedCategories()
    }

    override fun onResume() {
        super.onResume()
        // When returning to this screen, ensure the correct navigation item is selected
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.nav_groceries
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_groceries // Set this screen as selected

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_lists -> {
                    val intent = Intent(this, ListsActivity::class.java)
                    startActivity(intent)
                    true
                }
                // Add cases for Profile and Settings later
                else -> false
            }
        }
    }

    private fun setupCategoryFilters() {
        val categoryContainer = findViewById<LinearLayout>(R.id.category_filter_container)
        
        val categories = listOf("All", "Vegetables", "Fruits", "Dairy", "Bakery", "Meat", "Snacks")
        
        categories.forEachIndexed { index, category ->
            val filterChip = categoryContainer.getChildAt(index) as TextView
            categoryFilters.add(filterChip)
            
            filterChip.setOnClickListener {
                toggleCategory(category)
            }
        }
        
        // Set initial state
        updateFilterChipAppearance()
    }
    
    private fun setupGroceryGrid() {
        groceryGrid = findViewById(R.id.grocery_grid)
    }
    
    private fun setupShoppingCartClick() {
        val cartContainer = findViewById<FrameLayout>(R.id.cart_button_container)
        cartContainer.setOnClickListener {
            showShoppingList()
        }
    }
    
    private fun toggleCategory(category: String) {
        if (category == "All") {
            // If "All" is clicked, clear other selections and select only "All"
            selectedCategories.clear()
            selectedCategories.add("All")
        } else {
            // If "All" is currently selected and another category is clicked, remove "All"
            if (selectedCategories.contains("All")) {
                selectedCategories.remove("All")
            }
            
            // Toggle the clicked category
            if (selectedCategories.contains(category)) {
                selectedCategories.remove(category)
            } else {
                selectedCategories.add(category)
            }
            
            // If no categories are selected, default to "All"
            if (selectedCategories.isEmpty()) {
                selectedCategories.add("All")
            }
        }
        
        // Update UI
        updateFilterChipAppearance()
        updateProductsForSelectedCategories()
    }
    
    private fun updateFilterChipAppearance() {
        val categories = listOf("All", "Vegetables", "Fruits", "Dairy", "Bakery", "Meat", "Snacks")
        
        categoryFilters.forEachIndexed { index, chip ->
            val category = categories[index]
            if (selectedCategories.contains(category)) {
                // Selected state
                chip.background = ContextCompat.getDrawable(this, R.drawable.bg_chip_selected)
                chip.setTextColor(ContextCompat.getColor(this, R.color.text_on_primary))
            } else {
                // Unselected state
                chip.background = ContextCompat.getDrawable(this, R.drawable.bg_chip_unselected)
                chip.setTextColor(ContextCompat.getColor(this, R.color.text_secondary))
            }
        }
    }
    
    private fun updateProductsForSelectedCategories() {
        val allProducts = mutableListOf<String>()
        
        // Collect products from all selected categories
        selectedCategories.forEach { category ->
            val products = categoryProducts[category] ?: emptyList()
            allProducts.addAll(products)
        }
        
        // Remove duplicates to get all unique products
        val uniqueProducts = allProducts.distinct()
        
        // Clear existing grid
        groceryGrid.removeAllViews()
        
        // Create product items for all unique products
        uniqueProducts.forEach { productName ->
            createProductItem(productName)
        }
    }
    
    private fun createProductItem(productName: String) {
        val productLayout = LinearLayout(this).apply {
            layoutParams = GridLayout.LayoutParams().apply {
                width = 0
                height = GridLayout.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                setMargins(
                    resources.getDimensionPixelSize(R.dimen.spacing_sm),
                    resources.getDimensionPixelSize(R.dimen.spacing_sm),
                    resources.getDimensionPixelSize(R.dimen.spacing_sm),
                    resources.getDimensionPixelSize(R.dimen.spacing_sm)
                )
            }
            orientation = LinearLayout.VERTICAL
            gravity = android.view.Gravity.CENTER
            background = ContextCompat.getDrawable(this@HomeActivity, R.drawable.bg_card)
            isClickable = true
            isFocusable = true
            setPadding(
                resources.getDimensionPixelSize(R.dimen.padding_card),
                resources.getDimensionPixelSize(R.dimen.padding_card),
                resources.getDimensionPixelSize(R.dimen.padding_card),
                resources.getDimensionPixelSize(R.dimen.padding_card)
            )
        }
        
        // Create product image
        val productImage = android.widget.ImageView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.size_category_icon),
                resources.getDimensionPixelSize(R.dimen.size_category_icon)
            ).apply {
                bottomMargin = resources.getDimensionPixelSize(R.dimen.spacing_sm)
            }
            background = ContextCompat.getDrawable(this@HomeActivity, R.drawable.placeholder_item)
            contentDescription = productName
        }
        
        // Create product name text
        val productText = TextView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            text = productName
            setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.text_primary))
            textSize = resources.getDimension(R.dimen.text_size_body2) / resources.displayMetrics.scaledDensity
            typeface = android.graphics.Typeface.DEFAULT_BOLD
        }
        
        // Add views to layout
        productLayout.addView(productImage)
        productLayout.addView(productText)
        
        // Add click listener to show quantity dialog
        productLayout.setOnClickListener {
            showQuantityDialog(productName)
        }
        
        // Add to grid
        groceryGrid.addView(productLayout)
    }
    
    private fun showQuantityDialog(productName: String) {
        val dialogView = LayoutInflater.from(this).inflate(android.R.layout.simple_list_item_1, null)
        
        // Create custom dialog layout
        val dialogLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 48, 48, 48)
        }
        
        // Product name text
        val productText = TextView(this).apply {
            text = "Add $productName to list"
            textSize = 18f
            setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.text_primary))
            setPadding(0, 0, 0, 32)
        }
        
        // Quantity input
        val quantityInput = EditText(this).apply {
            hint = "Enter quantity"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
            setText("1")
            background = ContextCompat.getDrawable(this@HomeActivity, R.drawable.bg_search)
            setPadding(32, 24, 32, 24)
            setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.text_primary))
            setHintTextColor(ContextCompat.getColor(this@HomeActivity, R.color.text_hint))
        }
        
        dialogLayout.addView(productText)
        dialogLayout.addView(quantityInput)
        
        AlertDialog.Builder(this)
            .setTitle("Add to Shopping List")
            .setView(dialogLayout)
            .setPositiveButton("Add") { dialog, _ ->
                val quantity = quantityInput.text.toString().toIntOrNull() ?: 1
                addToShoppingList(productName, quantity)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    
    private fun addToShoppingList(productName: String, quantity: Int) {
        if (shoppingList.containsKey(productName)) {
            // If item already exists, add to existing quantity
            shoppingList[productName] = shoppingList[productName]!! + quantity
            Toast.makeText(this, "Updated $productName quantity to ${shoppingList[productName]}", Toast.LENGTH_SHORT).show()
        } else {
            // Add new item to list
            shoppingList[productName] = quantity
            Toast.makeText(this, "Added $quantity x $productName to shopping list", Toast.LENGTH_SHORT).show()
        }
        
        updateCartBadge()
    }
    
    private fun updateCartBadge() {
        val itemCount = shoppingList.size
        if (itemCount > 0) {
            cartBadge.visibility = View.VISIBLE
            cartBadge.text = itemCount.toString()
        } else {
            cartBadge.visibility = View.GONE
        }
    }
    
    private fun showShoppingList() {
        if (shoppingList.isEmpty()) {
            Toast.makeText(this, "Your shopping list is empty", Toast.LENGTH_SHORT).show()
            return
        }

        // --- 1. Create the Dialog Layout ---
        val dialogLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 48, 48, 48)
        }

        // Add Cart Icon and Title
        val cartIcon = ImageView(this).apply {
            layoutParams = LinearLayout.LayoutParams(64, 64).apply {
                gravity = android.view.Gravity.CENTER_HORIZONTAL
                bottomMargin = 24
            }
            setImageDrawable(ContextCompat.getDrawable(this@HomeActivity, R.drawable.ic_shopping_cart))
            setColorFilter(ContextCompat.getColor(this@HomeActivity, R.color.primary))
        }
        dialogLayout.addView(cartIcon)
        val titleText = TextView(this).apply {
            text = "Your Shopping List"
            textSize = 20f
            setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.text_primary))
            setPadding(0, 0, 0, 32)
            typeface = android.graphics.Typeface.DEFAULT_BOLD
            gravity = android.view.Gravity.CENTER
        }
        dialogLayout.addView(titleText)

        // Add a container for the list items that we can update
        val listContainer = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }
        dialogLayout.addView(listContainer)

        // Add Total Text
        val totalText = TextView(this).apply {
            text = "Total: ${shoppingList.values.sum()} items (${shoppingList.size} products)"
            textSize = 16f
            setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.primary))
            setPadding(0, 32, 0, 0)
            typeface = android.graphics.Typeface.DEFAULT_BOLD
        }
        dialogLayout.addView(totalText)


        // --- 2. Create the Dialog Shell ---
        // We create it here so it's available inside the item click listeners
        val dialog = AlertDialog.Builder(this)
            .setView(dialogLayout)
            .setPositiveButton("Continue Shopping") { d, _ -> d.dismiss() }
            .setNegativeButton("Clear List") { d, _ ->
                clearShoppingList()
                d.dismiss()
            }
            .setNeutralButton("Save List") { d, _ ->
                saveShoppingList()
                d.dismiss()
            }
            .create()

        // --- 3. Function to Refresh the List View ---
        // This function will clear and redraw the items. We'll call it whenever the list changes.
        fun refreshShoppingList() {
            listContainer.removeAllViews()
            shoppingList.forEach { (product, quantity) ->
                val itemLayout = LinearLayout(this).apply {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = android.view.Gravity.CENTER_VERTICAL
                    setPadding(0, 16, 0, 16)
                }
                val itemIcon = ImageView(this).apply {
                    layoutParams = LinearLayout.LayoutParams(32, 32).apply {
                        marginEnd = 16
                    }
                    setImageDrawable(ContextCompat.getDrawable(this@HomeActivity, R.drawable.ic_shopping_cart))
                    setColorFilter(ContextCompat.getColor(this@HomeActivity, R.color.accent))
                }
                val itemText = TextView(this).apply {
                    layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                    text = "$quantity x $product"
                    textSize = 16f
                    setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.text_primary))
                }
                val editIcon = ImageView(this).apply {
                    layoutParams = LinearLayout.LayoutParams(48, 48)
                    setPadding(8, 8, 8, 8)
                    setImageDrawable(ContextCompat.getDrawable(this@HomeActivity, R.drawable.ic_edit))
                    setColorFilter(ContextCompat.getColor(this@HomeActivity, R.color.text_secondary))
                    isClickable = true
                    isFocusable = true
                }
                val deleteIcon = ImageView(this).apply {
                    layoutParams = LinearLayout.LayoutParams(48, 48)
                    setPadding(8, 8, 8, 8)
                    setImageDrawable(ContextCompat.getDrawable(this@HomeActivity, R.drawable.ic_delete))
                    setColorFilter(ContextCompat.getColor(this@HomeActivity, R.color.error))
                    isClickable = true
                    isFocusable = true
                }

                // SET LISTENERS INSIDE THE LOOP
                deleteIcon.setOnClickListener {
                    shoppingList.remove(product)
                    Toast.makeText(this, "$product removed.", Toast.LENGTH_SHORT).show()
                    if (shoppingList.isEmpty()) {
                        dialog.dismiss()
                    } else {
                        refreshShoppingList() // Redraw the list
                    }
                    updateCartBadge()
                }

                editIcon.setOnClickListener {
                    showEditQuantityDialog(product) {
                        // After editing, just refresh the whole list to show changes
                        if (shoppingList.isEmpty()) {
                            dialog.dismiss()
                        } else {
                            refreshShoppingList()
                        }
                    }
                }

                itemLayout.addView(itemIcon)
                itemLayout.addView(itemText)
                itemLayout.addView(editIcon)
                itemLayout.addView(deleteIcon)
                listContainer.addView(itemLayout)
            }
            // Update total text
            totalText.text = "Total: ${shoppingList.values.sum()} items (${shoppingList.size} products)"
        }

        // --- 4. Initial Population and Show Dialog ---
        refreshShoppingList()
        dialog.show()
    }

    private fun showEditQuantityDialog(productName: String, onListChanged: () -> Unit) {
        val dialogLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 48, 48, 48)
        }

        val quantityInput = EditText(this).apply {
            hint = "Enter new quantity"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
            setText(shoppingList[productName].toString())
            background = ContextCompat.getDrawable(this@HomeActivity, R.drawable.bg_search)
            setPadding(32, 24, 32, 24)
            setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.text_primary))
            setHintTextColor(ContextCompat.getColor(this@HomeActivity, R.color.text_hint))
        }

        dialogLayout.addView(quantityInput)

        AlertDialog.Builder(this)
            .setTitle("Update Quantity for $productName")
            .setView(dialogLayout)
            .setPositiveButton("Update") { dialog, _ ->
                val newQuantity = quantityInput.text.toString().toIntOrNull()
                if (newQuantity != null && newQuantity > 0) {
                    shoppingList[productName] = newQuantity
                    Toast.makeText(this, "$productName quantity updated.", Toast.LENGTH_SHORT).show()
                } else {
                    shoppingList.remove(productName)
                    Toast.makeText(this, "$productName removed.", Toast.LENGTH_SHORT).show()
                }
                updateCartBadge()
                onListChanged() // Call the callback to refresh the list
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun clearShoppingList() {
        shoppingList.clear()
        Toast.makeText(this, "Shopping list cleared", Toast.LENGTH_SHORT).show()
        updateCartBadge()
    }
    
    private fun saveShoppingList() {
        if (shoppingList.isEmpty()) {
            Toast.makeText(this, "Nothing to save", Toast.LENGTH_SHORT).show()
            return
        }

        // --- Create a dialog to get the list name ---
        val dialogLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 48, 48, 48)
        }

        val listNameInput = EditText(this).apply {
            hint = "Enter list name"
            setText("My Shopping List")
            background = ContextCompat.getDrawable(this@HomeActivity, R.drawable.bg_search)
            setPadding(32, 24, 32, 24)
            setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.text_primary))
            setHintTextColor(ContextCompat.getColor(this@HomeActivity, R.color.text_hint))
        }
        dialogLayout.addView(listNameInput)

        AlertDialog.Builder(this)
            .setTitle("Save Shopping List")
            .setView(dialogLayout)
            .setPositiveButton("Save") { dialog, _ ->
                val listName = listNameInput.text.toString().trim()
                if (listName.isNotEmpty()) {
                    // In a real app, this would save to a database.
                    // For now, we just show a confirmation toast and clear the list.
                    Toast.makeText(this, "List '$listName' saved!", Toast.LENGTH_LONG).show()
                    clearShoppingList()
                } else {
                    Toast.makeText(this, "List name cannot be empty.", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
