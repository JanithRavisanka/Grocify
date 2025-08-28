# Profile Page Documentation

## Overview
The Profile page displays user information, statistics, and account management options. It shows a profile picture, quick stats (lists, items, days), and various account-related actions like personal information, preferences, and security settings.

## How It Works (Simple Explanation)

### 1. **What Users See**
- A beautiful profile header with gradient background
- Profile picture with edit button (currently non-functional)
- Quick stats showing: 12 Lists, 247 Items, 30 Days
- Account information section with personal info, preferences, and security
- Action buttons for logout and delete account
- Bottom navigation bar

### 2. **What Happens When Users Interact**
- Users can tap the back button to return to the previous page
- Users can tap the edit button (currently non-functional)
- Users can tap various account items (currently non-functional)
- Users can tap logout or delete account buttons (currently non-functional)
- Bottom navigation lets users go to other pages
- Users can scroll through all profile options

## Code Structure

### XML Layout File: `activity_profile.xml`

#### **Page Structure (Top to Bottom)**
```xml
ConstraintLayout (main container)
├── Profile Header (always visible)
│   ├── Header Bar (back button + title + edit button)
│   └── Profile Picture and Info
├── Scrollable Content Area
│   ├── Quick Stats Section (Lists, Items, Days)
│   ├── Account Information Section
│   │   ├── Personal Information
│   │   ├── Preferences
│   │   └── Security
│   └── Action Buttons (Logout, Delete Account)
└── Bottom Navigation Bar
```

#### **Key Elements Explained**

**1. Profile Header Section**
```xml
<LinearLayout
    android:id="@+id/profile_header"
    android:background="@drawable/bg_profile_header"  <!-- Gradient background -->
    android:paddingTop="@dimen/spacing_xxl"           <!-- Extra top spacing -->
    android:paddingBottom="@dimen/spacing_xl">        <!-- Bottom spacing -->
    
    <!-- Header Bar -->
    <LinearLayout
        android:orientation="horizontal">              <!-- Elements side by side -->
        
        <!-- Back Button -->
        <ImageView
            android:id="@+id/back_button"
            android:src="@drawable/ic_back"            <!-- Back arrow icon -->
            android:clickable="true"                   <!-- Makes it tappable -->
            app:tint="@color/text_on_primary" />       <!-- White color -->
        
        <!-- Title -->
        <TextView
            android:text="Profile"                     <!-- Page title -->
            android:textColor="@color/text_on_primary" /> <!-- White text -->
        
        <!-- Edit Button -->
        <ImageView
            android:id="@+id/edit_button"
            android:src="@drawable/ic_edit"            <!-- Edit icon -->
            android:clickable="true"                   <!-- Makes it tappable -->
            app:tint="@color/text_on_primary" />       <!-- White color -->
    </LinearLayout>
    
    <!-- Profile Picture Container -->
    <FrameLayout
        android:layout_width="120dp"                   <!-- Picture size -->
        android:layout_height="120dp">
        
        <!-- Profile Picture -->
        <ImageView
            android:id="@+id/profile_picture"
            android:background="@drawable/bg_profile_picture"  <!-- Circular background -->
            android:src="@drawable/ic_profile"                 <!-- Profile icon -->
            app:tint="@color/primary" />                        <!-- Green color -->
    </FrameLayout>
</LinearLayout>
```
- Creates a gradient header with profile information
- Contains back button, page title, and edit button
- Shows a large circular profile picture
- Uses white text and icons for contrast

**2. Quick Stats Section**
```xml
<LinearLayout
    android:background="@drawable/bg_card"            <!-- Card background -->
    android:orientation="horizontal">                 <!-- Stats side by side -->
    
    <!-- Lists Created -->
    <LinearLayout
        android:layout_weight="1"                     <!-- Equal width -->
        android:gravity="center"                      <!-- Center content -->
        android:orientation="vertical">               <!-- Text stacked -->
        
        <TextView
            android:id="@+id/lists_count"
            android:text="12"                          <!-- Number of lists -->
            android:textColor="@color/primary"         <!-- Green color -->
            android:textSize="@dimen/text_size_h2"     <!-- Large text size -->
            android:textStyle="bold" />                <!-- Bold text -->
        
        <TextView
            android:text="Lists"                       <!-- Label text -->
            android:textColor="@color/text_secondary"  <!-- Gray color -->
            android:textSize="@dimen/text_size_caption" /> <!-- Small text size -->
    </LinearLayout>
    
    <!-- Items Purchased -->
    <LinearLayout
        android:layout_weight="1"
        android:gravity="center"
        android:orientation="vertical">
        
        <TextView
            android:id="@+id/items_count"
            android:text="247"                         <!-- Number of items -->
            android:textColor="@color/accent"          <!-- Accent color -->
            android:textSize="@dimen/text_size_h2"
            android:textStyle="bold" />
        
        <TextView
            android:text="Items"                       <!-- Label text -->
            android:textColor="@color/text_secondary"
            android:textSize="@dimen/text_size_caption" />
    </LinearLayout>
    
    <!-- Days Active -->
    <LinearLayout
        android:layout_weight="1"
        android:gravity="center"
        android:orientation="vertical">
        
        <TextView
            android:id="@+id/days_active"
            android:text="30"                          <!-- Number of days -->
            android:textColor="@color/success"         <!-- Success color -->
            android:textSize="@dimen/text_size_h2"
            android:textStyle="bold" />
        
        <TextView
            android:text="Days"                        <!-- Label text -->
            android:textColor="@color/text_secondary"
            android:textSize="@dimen/text_size_caption" />
    </LinearLayout>
</LinearLayout>
```
- Creates a horizontal card with three statistics
- Each stat shows a number and label
- Uses different colors for visual distinction
- Equal width distribution for balanced layout

**3. Account Information Items**
```xml
<!-- Personal Information -->
<LinearLayout
    android:id="@+id/personal_info_item"
    android:background="@drawable/bg_profile_item"    <!-- Item background -->
    android:clickable="true"                          <!-- Makes it tappable -->
    android:gravity="center_vertical"                 <!-- Center content vertically -->
    android:orientation="horizontal">                 <!-- Icon and text side by side -->
    
    <ImageView
        android:src="@drawable/ic_profile"            <!-- Profile icon -->
        app:tint="@color/primary" />                  <!-- Green color -->
    
    <LinearLayout
        android:layout_weight="1"                     <!-- Takes available width -->
        android:orientation="vertical">               <!-- Text stacked -->
        
        <TextView
            android:text="Personal Information"        <!-- Item title -->
            android:textColor="@color/text_primary"    <!-- Dark text -->
            android:textStyle="bold" />                <!-- Bold text -->
        
        <TextView
            android:text="Edit your name, email, and other details"  <!-- Description -->
            android:textColor="@color/text_secondary"  <!-- Gray text -->
            android:textSize="@dimen/text_size_body2" /> <!-- Small text -->
    
    <ImageView
        android:src="@drawable/ic_back"               <!-- Arrow icon -->
        android:rotation="180"                        <!-- Rotated 180 degrees -->
        app:tint="@color/text_hint" />                <!-- Light gray color -->
</LinearLayout>
```
- Creates clickable items for account management
- Each item has an icon, title, description, and arrow
- Uses consistent styling and spacing
- Currently non-functional but visually complete

**4. Action Buttons**
```xml
<!-- Logout Button -->
<com.google.android.material.button.MaterialButton
    android:id="@+id/logout_button"
    android:layout_width="match_parent"               <!-- Full width -->
    android:layout_height="56dp"                     <!-- Button height -->
    android:text="Logout"                            <!-- Button text -->
    android:textColor="@color/error"                 <!-- Red text -->
    app:backgroundTint="@null"                       <!-- No background -->
    app:strokeColor="@color/error"                   <!-- Red border -->
    app:strokeWidth="1dp" />                         <!-- Border width -->

<!-- Delete Account Link -->
<TextView
    android:id="@+id/delete_account_link"
    android:layout_width="wrap_content"               <!-- Text width only -->
    android:layout_height="wrap_content"
    android:text="Delete Account"                     <!-- Link text -->
    android:textColor="@color/text_hint"              <!-- Light gray text -->
    android:clickable="true"                         <!-- Makes it tappable -->
    android:background="?attr/selectableItemBackgroundBorderless" /> <!-- Ripple effect -->
```
- Creates a logout button with red border and text
- Creates a delete account text link
- Both are clickable but currently non-functional

### Kotlin Code File: `ProfileActivity.kt`

#### **What the Code Does**

**1. Setting Up the Page**
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_profile)  // Loads the XML layout
    
    setupBottomNavigation()  // Sets up bottom navigation
}
```
- Loads the profile page layout
- Sets up navigation functionality

**2. Bottom Navigation Setup**
```kotlin
private fun setupBottomNavigation() {
    val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
    
    // Sets current page as selected
    bottomNav.selectedItemId = R.id.nav_profile
    
    // Handles navigation to other pages
    bottomNav.setOnItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.nav_groceries -> {                  // Go to Home page
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            R.id.nav_lists -> {                      // Go to Lists page
                val intent = Intent(this, ListsActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            R.id.nav_profile -> true                 // Already on profile page
            R.id.nav_settings -> {                   // Go to Settings page
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
        }
    }
}
```
- Sets up bottom navigation bar
- Marks current page as selected
- Handles navigation to other pages using `Intent`

## Profile Information Displayed

### **Quick Stats**
- **Lists Created**: 12
- **Items Purchased**: 247
- **Days Active**: 30

### **Account Information Items**
1. **Personal Information**
   - Description: "Edit your name, email, and other details"
   - Icon: Profile icon
   - Currently non-functional

2. **Preferences**
   - Description: "Notifications, theme, and app settings"
   - Icon: Settings icon
   - Currently non-functional

3. **Security**
   - Description: "Change password and security settings"
   - Icon: Settings icon
   - Currently non-functional

### **Action Buttons**
- **Logout Button**: Red outlined button
- **Delete Account Link**: Small text link below logout

## User Experience Flow

```
1. User opens Profile page → Sees profile header and stats
2. User scrolls down to see account options
3. User can tap back button to return to previous page
4. User can use bottom navigation to go to other pages
5. User can view their shopping statistics
```

## Technical Details

### **Layout System: ConstraintLayout**
- Main page uses `ConstraintLayout` for overall structure
- Profile header is fixed at the top
- Content area is scrollable between header and bottom navigation
- Bottom navigation is fixed at the bottom

### **Header Design System**
- Uses gradient background for visual appeal
- Profile picture with circular background
- Consistent spacing and typography
- White text and icons for contrast

### **Stats Display System**
- Three-column layout with equal width distribution
- Different colors for each statistic type
- Large numbers with descriptive labels
- Card background for visual separation

### **Account Item System**
- Consistent item design with icons and descriptions
- Clickable items with visual feedback
- Arrow indicators for navigation
- Currently non-functional but visually complete

### **Navigation System**
- Back button for returning to previous page
- Bottom navigation for moving between main sections
- Proper page selection indication

## Common Questions

**Q: How do I edit my profile information?**
A: The edit button and personal information item exist but currently don't work. In a real app, these would open profile editing forms.

**Q: What do the statistics mean?**
A: The stats show your app usage: how many shopping lists you've created, how many items you've purchased, and how many days you've been using the app.

**Q: Can I change my preferences or security settings?**
A: The preferences and security items exist but currently don't work. In a real app, these would open settings pages.

**Q: How do I logout or delete my account?**
A: The logout and delete account buttons exist but currently don't work. In a real app, these would handle account management.

**Q: Why is the profile picture just an icon?**
A: Currently, the profile picture shows a default icon. In a real app, this would display the user's actual profile photo.

## Summary
The Profile page displays user information and account management options in an attractive, organized layout. The XML creates the visual design with a gradient header, statistics display, and account management items, while the Kotlin code handles navigation between pages. Users can view their shopping statistics, access account options, and navigate to other parts of the app. The page uses a combination of fixed header/footer elements and scrollable content for optimal user experience.
