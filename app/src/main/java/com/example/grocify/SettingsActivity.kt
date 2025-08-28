package com.example.grocify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SettingsActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Starting SettingsActivity")
        setContentView(R.layout.activity_settings)
        Log.d(TAG, "onCreate: Layout set")
        setupBottomNavigation()
        Log.d(TAG, "onCreate: Bottom navigation setup complete")
    }

    private fun setupBottomNavigation() {
        Log.d(TAG, "setupBottomNavigation: Starting setup")
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        if (bottomNav != null) {
            Log.d(TAG, "setupBottomNavigation: Bottom navigation found")
            bottomNav.selectedItemId = R.id.nav_settings
            Log.d(TAG, "setupBottomNavigation: Selected item set to nav_settings")
            
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
                        Log.d(TAG, "setupBottomNavigation: Already on settings page")
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
        Log.d(TAG, "onResume: SettingsActivity resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: SettingsActivity paused")
    }
}
