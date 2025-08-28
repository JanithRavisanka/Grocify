package com.example.grocify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ProfileActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Starting ProfileActivity")
        setContentView(R.layout.activity_profile)

        setupBottomNavigation()
        Log.d(TAG, "onCreate: Setup complete")
    }

    private fun setupBottomNavigation() {
        Log.d(TAG, "setupBottomNavigation: Starting setup")
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        if (bottomNav != null) {
            Log.d(TAG, "setupBottomNavigation: Bottom navigation found")
            bottomNav.selectedItemId = R.id.nav_profile
            Log.d(TAG, "setupBottomNavigation: Selected item set to nav_profile")
            
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
                        Log.d(TAG, "setupBottomNavigation: Already on profile page")
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
        Log.d(TAG, "onResume: ProfileActivity resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onResume: ProfileActivity paused")
    }
}
