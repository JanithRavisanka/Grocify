package com.example.grocify

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

// --- New, more detailed data classes ---
data class ListItem(
    val name: String,
    val quantity: Int,
    var isChecked: Boolean = false
)

data class SavedList(
    val name: String,
    var items: MutableList<ListItem>,
    val date: String
) {
    // Helper properties to calculate totals and progress on the fly
    val itemCount: Int
        get() = items.sumOf { it.quantity }
    val progress: Int
        get() {
            if (items.isEmpty()) return 0
            val checkedCount = items.count { it.isChecked }
            return (checkedCount * 100) / items.size
        }
}

class ListsActivity : AppCompatActivity() {

    // --- Use the new data model for our dummy data ---
    private val dummyLists = mutableListOf(
        SavedList("Weekly Groceries", mutableListOf(
            ListItem("Milk", 2), ListItem("Bread", 1), ListItem("Apples", 5, true), ListItem("Chicken", 1)
        ), "Saved on Oct 26"),
        SavedList("Weekend BBQ", mutableListOf(
            ListItem("Sausages", 8, true), ListItem("Buns", 8, true), ListItem("Ketchup", 1)
        ), "Saved on Oct 24"),
        SavedList("Pantry Stock-up", mutableListOf(
            ListItem("Flour", 1), ListItem("Sugar", 1, true), ListItem("Canned Tomatoes", 4)
        ), "Saved on Oct 21")
    )

    private lateinit var listsContainer: LinearLayout
    private lateinit var inflater: LayoutInflater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lists)

        listsContainer = findViewById(R.id.lists_container)
        inflater = LayoutInflater.from(this)

        setupBackButton()
        setupBottomNavigation()
        refreshListsUI() // Initial population
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_lists // Set this screen as selected

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_groceries -> {
                    finish() // Go back to HomeActivity
                    true
                }
                else -> false
            }
        }
    }

    private fun setupBackButton() {
        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }

    // --- New function to redraw the entire list UI ---
    private fun refreshListsUI() {
        listsContainer.removeAllViews()
        dummyLists.forEach { list ->
            val itemView = inflater.inflate(R.layout.item_saved_list, listsContainer, false)

            itemView.findViewById<TextView>(R.id.list_name).text = list.name
            itemView.findViewById<TextView>(R.id.item_count).text = "${list.itemCount} items"
            itemView.findViewById<TextView>(R.id.list_date).text = list.date
            itemView.findViewById<TextView>(R.id.progress_text).text = "${list.progress}% complete"
            itemView.findViewById<ProgressBar>(R.id.progress_bar).progress = list.progress

            itemView.setOnClickListener {
                showListDetailPopup(list)
            }

            itemView.findViewById<ImageView>(R.id.delete_icon).setOnClickListener {
                dummyLists.remove(list)
                refreshListsUI()
                Toast.makeText(this, "'${list.name}' deleted.", Toast.LENGTH_SHORT).show()
            }
             itemView.findViewById<ImageView>(R.id.share_icon).setOnClickListener {
                Toast.makeText(this, "Share '${list.name}'", Toast.LENGTH_SHORT).show()
            }

            listsContainer.addView(itemView)
        }
    }

    private fun showListDetailPopup(list: SavedList) {
        val dialogLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 48, 48, 48)
        }

        val titleText = TextView(this).apply {
            text = list.name
            textSize = 20f
            setTextColor(android.graphics.Color.BLACK) // Changed to black for title
            setPadding(0, 0, 0, 32)
            typeface = android.graphics.Typeface.DEFAULT_BOLD
            gravity = android.view.Gravity.CENTER
        }
        dialogLayout.addView(titleText)

        // Add items with checkboxes
        list.items.forEach { listItem ->
            val itemLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(0, 8, 0, 8)
                gravity = android.view.Gravity.CENTER_VERTICAL
            }

            val checkBox = CheckBox(this).apply {
                isChecked = listItem.isChecked
            }

            val itemText = TextView(this).apply {
                text = "${listItem.quantity} x ${listItem.name}"
                textSize = 16f
                setTextColor(if (listItem.isChecked) android.graphics.Color.GRAY else R.color.text_secondary)
                paintFlags = if (listItem.isChecked) paintFlags or Paint.STRIKE_THRU_TEXT_FLAG else paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
            
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                listItem.isChecked = isChecked
                itemText.setTextColor(if (isChecked) android.graphics.Color.GRAY else R.color.text_secondary)
                itemText.paintFlags = if (isChecked) itemText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG else itemText.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
            
            itemLayout.addView(checkBox)
            itemLayout.addView(itemText)
            dialogLayout.addView(itemLayout)
        }

        AlertDialog.Builder(this)
            .setView(dialogLayout)
            .setPositiveButton("Close") { dialog, _ ->
                refreshListsUI() // Update the main screen when dialog is closed
                dialog.dismiss()
            }
            .show()
    }
}
