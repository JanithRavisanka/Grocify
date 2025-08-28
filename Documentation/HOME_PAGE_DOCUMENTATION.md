# Home Page Documentation

## Overview
The Home page is the main grocery shopping interface where users can browse grocery items, filter by categories, and navigate to other parts of the app. It's the central hub of the Grocify app.

## How It Works (Simple Explanation)

### 1. **What Users See**
- A header bar with back button, app title, and shopping cart
- A main title "Select Your Groceries"
- A search bar for finding products
- Category filter buttons (All, Vegetables, Fruits, Dairy, Bakery, Meat, Snacks)
- A grid of grocery items with images and names
- Bottom navigation bar

### 2. **What Happens When Users Interact**
- Users can tap category filters to see different types of groceries
- The selected category button changes color (green with white text)
- Other category buttons become unselected (white with gray text)
- Users can scroll through grocery items
- Bottom navigation lets users go to Lists, Profile, or Settings

## Code Structure

### XML Layout File: `activity_home.xml`

#### **Page Structure (Top to Bottom)**
```xml
ConstraintLayout (main container)
├── Header Bar (back button + title + shopping cart)
├── Main Title ("Select Your Groceries")
├── Search Bar (with search icon and input field)
├── Category Filters (horizontal scrollable row of buttons)
├── Grocery Items Grid (2-column grid of food items)
└── Bottom Navigation Bar
```

#### **Key Elements Explained**

**1. Header Bar**
```xml
<LinearLayout
    android:id="@+id/header_bar"
    android:background="@color/primary"           <!-- Green background -->
    android:orientation="horizontal">             <!-- Elements side by side -->
    
    <!-- Back Button -->
    <ImageView
        android:src="@drawable/ic_back"           <!-- Back arrow icon -->
        app:tint="@color/text_on_primary" />      <!-- White color -->
    
    <!-- App Title -->
    <TextView
        android:text="@string/app_name"           <!-- "Grocify" text -->
        android:textColor="@color/text_on_primary" /> <!-- White text -->
    
    <!-- Shopping Cart -->
    <FrameLayout android:id="@+id/cart_button_container">
        <ImageView android:src="@drawable/ic_shopping_cart" />
        <TextView android:id="@+id/cart_badge" /> <!-- Cart item count -->
    </FrameLayout>
</LinearLayout>
```
- Creates a green header bar
- Contains back button, app title, and shopping cart
- Uses white text and icons for contrast

**2. Category Filter Buttons**
```xml
<HorizontalScrollView>                    <!-- Makes buttons scrollable horizontally -->
    <LinearLayout android:orientation="horizontal">
        
        <!-- All Categories Button (Selected by default) -->
        <TextView
            android:id="@+id/category_filter_all"
            android:background="@drawable/bg_chip_selected"    <!-- Green background -->
            android:textColor="@color/text_on_primary"         <!-- White text -->
            android:text="@string/all"                         <!-- "All" text -->
            android:clickable="true" />                        <!-- Makes it tappable -->
        
        <!-- Vegetables Button (Unselected) -->
        <TextView
            android:id="@+id/category_filter_vegetables"
            android:background="@drawable/bg_chip_unselected"  <!-- White background -->
            android:textColor="@color/text_secondary"          <!-- Gray text -->
            android:text="@string/category_vegetables"         <!-- "Vegetables" text -->
            android:clickable="true" />
        
        <!-- More category buttons... -->
    </LinearLayout>
</HorizontalScrollView>
```
- Creates horizontal scrollable row of category buttons
- "All" button starts selected (green with white text)
- Other buttons start unselected (white with gray text)
- All buttons are clickable

**3. Grocery Items Grid**
```xml
<ScrollView>                              <!-- Makes items scrollable vertically -->
    <GridLayout
        android:id="@+id/grocery_grid"
        android:columnCount="2"           <!-- 2 columns of items -->
        android:useDefaultMargins="false"> <!-- Custom spacing -->
        
        <!-- Grocery Item 1 - Carrot -->
        <LinearLayout
            android:layout_width="0dp"    <!-- Takes available width -->
            android:layout_columnWeight="1" <!-- Equal width in grid -->
            android:background="@drawable/bg_card"     <!-- Card background -->
            android:clickable="true">                   <!-- Makes it tappable -->
            
            <ImageView
                android:src="@drawable/carrot"          <!-- Carrot image -->
                android:layout_width="80dp"             <!-- Image size -->
                android:layout_height="80dp" />
            
            <TextView
                android:text="Carrot"                   <!-- Item name -->
                android:textStyle="bold" />             <!-- Bold text -->
        </LinearLayout>
        
        <!-- More grocery items... -->
    </GridLayout>
</ScrollView>
```
- Creates a 2-column grid of grocery items
- Each item is a card with image and name
- Items are clickable (though no action currently)
- Grid automatically wraps to new rows

### Kotlin Code File: `HomeActivity.kt`

#### **What the Code Does**

**1. Setting Up the Page**
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)  // Loads the XML layout
    
    setupCategoryFilters()      // Sets up category buttons
    setupBottomNavigation()     // Sets up bottom navigation
}
```

**2. Category Filter System**
```kotlin
private fun setupCategoryFilters() {
    // Finds all category filter buttons in XML
    categoryFilters = listOf(
        findViewById(R.id.category_filter_all),        // "All" button
        findViewById(R.id.category_filter_vegetables), // "Vegetables" button
        findViewById(R.id.category_filter_fruits),     // "Fruits" button
        findViewById(R.id.category_filter_dairy),      // "Dairy" button
        findViewById(R.id.category_filter_bakery),     // "Bakery" button
        findViewById(R.id.category_filter_meat),       // "Meat" button
        findViewById(R.id.category_filter_snacks)      // "Snacks" button
    )
    
    // Makes each button respond to taps
    categoryFilters.forEachIndexed { index, filter ->
        filter.setOnClickListener {
            selectCategory(index, filter.text.toString())  // Handles category selection
        }
    }
    
    // Sets "All" as selected by default
    selectCategory(0, "all")
}
```

**3. Category Selection Logic**
```kotlin
private fun selectCategory(index: Int, category: String) {
    // Updates visual appearance of all buttons
    categoryFilters.forEachIndexed { i, filter ->
        if (i == index) {
            // Selected button: green background, white text
            filter.setBackgroundResource(R.drawable.bg_chip_selected)
            filter.setTextColor(resources.getColor(R.color.text_on_primary, null))
        } else {
            // Unselected buttons: white background, gray text
            filter.setBackgroundResource(R.drawable.bg_chip_unselected)
            filter.setTextColor(resources.getColor(R.color.text_secondary, null))
        }
    }
    
    // Updates which grocery items to show
    updateGroceryItems(category.lowercase())
}
```

**4. Grocery Items Filtering**
```kotlin
private fun updateGroceryItems(category: String) {
    // Shows different items based on selected category
    when (category) {
        "all" -> showAllGroceryItems()           // Shows all 20 items
        "vegetables" -> showVegetableItems()     // Shows: carrot, tomato, potato
        "fruits" -> showFruitItems()             // Shows: apple, banana, orange, strawberry, watermelon, grapes
        "dairy" -> showDairyItems()              // Shows: milk, cheese, butter
        "bakery" -> showBakeryItems()            // Shows: bread, cake, bagel
        "meat" -> showMeatItems()                // Shows: chicken, beef
        "snacks" -> showSnackItems()             // Shows: chips, cookies, crackers
    }
}
```

**5. Bottom Navigation Setup**
```kotlin
private fun setupBottomNavigation() {
    val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
    
    // Sets current page as selected
    bottomNav.selectedItemId = R.id.nav_groceries
    
    // Handles navigation to other pages
    bottomNav.setOnItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.nav_groceries -> true           // Already on groceries page
            R.id.nav_lists -> {                  // Go to Lists page
                val intent = Intent(this, ListsActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            R.id.nav_profile -> {                // Go to Profile page
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            R.id.nav_settings -> {               // Go to Settings page
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
        }
    }
}
```

## User Experience Flow

```
1. User opens Home page → Sees all grocery items
2. User taps "Vegetables" category → Button turns green, shows only vegetables
3. User taps "Fruits" category → Vegetables button turns white, Fruits button turns green
4. User scrolls through grocery items
5. User taps bottom navigation to go to other pages
```

## Technical Details

### **Layout System: ConstraintLayout**
- Uses `ConstraintLayout` for precise positioning
- Elements are positioned relative to each other and screen edges
- Makes layout responsive to different screen sizes

### **Category Filter System**
- Uses `HorizontalScrollView` for scrollable category buttons
- Each button has unique ID for Kotlin to find
- Background and text colors change based on selection state
- Uses drawable resources for different button states

### **Grocery Items Display**
- Uses `GridLayout` for organized item display
- Each item is a `LinearLayout` with image and text
- Items are organized in 2 columns
- Uses `ScrollView` for vertical scrolling

### **Navigation System**
- Bottom navigation uses `BottomNavigationView`
- Navigation handled by `Intent` and `startActivity`
- `finish()` closes current page when navigating

## Grocery Items by Category

### **All Categories (20 items)**
- Vegetables: Carrot, Tomato, Potato
- Fruits: Apple, Banana, Orange, Strawberry, Watermelon, Grapes
- Dairy: Milk, Cheese, Butter
- Bakery: Bread, Cake, Bagel
- Meat: Chicken, Beef
- Snacks: Chips, Cookies, Crackers

### **Individual Categories**
- **Vegetables**: Carrot, Tomato, Potato
- **Fruits**: Apple, Banana, Orange, Strawberry, Watermelon, Grapes
- **Dairy**: Milk, Cheese, Butter
- **Bakery**: Bread, Cake, Bagel
- **Meat**: Chicken, Beef
- **Snacks**: Chips, Cookies, Crackers

## Common Questions

**Q: How do category filters work?**
A: When you tap a category button, it turns green and shows only items from that category. Other buttons turn white.

**Q: Can I search for specific items?**
A: The search bar exists but currently doesn't have search functionality implemented.

**Q: What happens when I tap a grocery item?**
A: Currently, tapping items doesn't do anything. In a real app, you'd add them to a shopping cart.

**Q: How does the app know which items belong to which category?**
A: The Kotlin code has hardcoded lists for each category. In a real app, this would come from a database.

**Q: Why do some buttons scroll horizontally?**
A: The category filters use `HorizontalScrollView` so all categories fit on smaller screens.

## Summary
The Home page is a comprehensive grocery browsing interface with category filtering, item display, and navigation. The XML creates the visual layout with category buttons and grocery item grid, while the Kotlin code handles user interactions, category selection, and navigation between pages. Users can filter groceries by category and navigate to other parts of the app using the bottom navigation bar.
