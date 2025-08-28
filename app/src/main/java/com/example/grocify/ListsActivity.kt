package com.example.grocify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ListsActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ListsActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Starting ListsActivity")
        setContentView(R.layout.activity_lists)
        Log.d(TAG, "onCreate: Layout set")

        setupBackButton()
        setupBottomNavigation()
        Log.d(TAG, "onCreate: Setup complete")
    }

    private fun setupBackButton() {
        Log.d(TAG, "setupBackButton: Setting up back button")
        val backButton = findViewById<ImageView>(R.id.back_button)
        if (backButton != null) {
            backButton.setOnClickListener {
                Log.d(TAG, "setupBackButton: Back button clicked")
                finish()
            }
            Log.d(TAG, "setupBackButton: Back button listener set")
        } else {
            Log.e(TAG, "setupBackButton: Back button not found!")
        }
    }

    private fun setupBottomNavigation() {
        Log.d(TAG, "setupBottomNavigation: Starting setup")
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        if (bottomNav != null) {
            Log.d(TAG, "setupBottomNavigation: Bottom navigation found")
            bottomNav.selectedItemId = R.id.nav_lists
            Log.d(TAG, "setupBottomNavigation: Selected item set to nav_lists")
            
            bottomNav.setOnItemSelectedListener { menuItem ->
                Log.d(TAG, "setupBottomNavigation: Item selected: ${menuItem.itemId}")
                when (menuItem.itemId) {
                    R.id.nav_groceries -> {
                        Log.d(TAG, "setupBottomNavigation: Navigating to HomeActivity")
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                        true
                    }
                    R.id.nav_lists -> {
                        Log.d(TAG, "setupBottomNavigation: Already on lists page")
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
        Log.d(TAG, "onResume: ListsActivity resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ListsActivity paused")
    }
}
