ROLE
You are an Android XML UI designer. Produce a clean, modern **XML-only** Android Studio project for a grocery app called **Grocify**. The user flow is:
**Categories → browse items → select → build list → save**.
Do NOT add Kotlin/Java. Where navigation is required, add clear XML comments that describe the intended Intent target (e.g., <!-- NAV: to CategoryItemsActivity -->).

-----------------------------------
🎯 EXAM MARKING RULES TO FOLLOW
(From student's Lab Exam 02: XML-only UI, color rule, layouts, views, interactivity via intents, creativity)
-----------------------------------
1) Ideation (2) — The app is a mobile grocery organizer. Target: everyday shoppers; Problem: fast list creation from categories; Core features: category browsing, item selection, list builder, saved lists. 
2) Colors 60-30-10 (2) — Define `colors.xml` with a consistent palette and apply:
   • Primary 60% (background surfaces) 
   • Secondary 30% (cards, bars) 
   • Accent 10% (CTA buttons, highlights)
3) Layouts (2) — Use:
   • ConstraintLayout for main screens
   • LinearLayout (vertical/horizontal) for forms/rows
   • FrameLayout for splash or overlays
   • ScrollView/NestedScrollView where content can overflow
   Apply ample margins/padding.
4) Views (2) — Include TextView, EditText, Button, ImageView; also show RecyclerView/ListView placeholders for lists. All user-visible text in `strings.xml`.
5) Interactivity (1) — Model **Intent navigation** with explicit XML comments on clickable components (no code). Use `android:clickable="true"` and `android:focusable="true"` where needed and comment the target Activity.
6) Creativity (1) — Modern grocery theme, nice iconography, spacing, and hierarchy.

-----------------------------------
📱 MAIN WINDOWS & FUNCTIONALITIES (CATEGORIES → LIST BUILDER FLOW)
-----------------------------------
A) Splash (activity_splash.xml; FrameLayout)
   - Centered logo + tagline
   - <!-- NAV: to HomeActivity after delay -->

B) Home / Categories Grid (activity_home.xml; ConstraintLayout)
   - App bar with title "Grocify"
   - Search EditText (hint: "Search categories")
   - RecyclerView (Grid, 2–3 columns) of category cards:
       * ImageView (category icon), TextView (name)
       * Examples: Fruits, Vegetables, Dairy, Bakery, Meat, Snacks, Beverages, Household
   - FAB "My Lists"
   - Click behaviors (as comments):
       • Category card <!-- NAV: to CategoryItemsActivity with categoryId -->

C) Category Items (activity_category_items.xml; ConstraintLayout)
   - App bar with category name + back
   - Filter row (Chip-like buttons using TextViews or Material style if available) and a sort dropdown mock (Spinner)
   - RecyclerView of **Item Card** rows:
       • ImageView (item)
       • Texts: name, unit (e.g., 1kg), optional price placeholder
       • Small quantity stepper (− / quantity / +) using three Buttons
       • "Add" Button (accent)
       • Comment navs:
         - Quantity stepper: visual only
         - "Add": adds to "Selected Items" panel (visual only)
   - Bottom sticky "Selected Items" mini-panel:
       • Text: "{N} selected"
       • Button: "Build List" <!-- NAV: to ListBuilderActivity -->

D) List Builder (activity_list_builder.xml; ConstraintLayout or BottomSheet-like)
   - Title: "Build List"
   - EditText: "List name"
   - Recycler-like column of selected items with quantity and a remove (🗑) ImageView
   - Summary row: item count
   - Buttons: "Save List" (accent), "Clear"
   - Comments:
       • "Save List" <!-- NAV: to ListsActivity (and visually show the new list) -->

E) Saved Lists (activity_lists.xml; ConstraintLayout)
   - Title: "My Lists"
   - RecyclerView of list cards:
       • Texts: List name, created date, item count
       • Icons: Share, Delete (ImageViews)
       • Card click <!-- NAV: to ListDetailActivity with listId -->
   - Empty state placeholder when no lists
   - FAB: "New from Categories" <!-- NAV: to HomeActivity -->

F) List Detail (activity_list_detail.xml; ConstraintLayout)
   - Title: List name
   - Search within list
   - RecyclerView of items with:
       • Checkbox (purchased), item name, quantity
       • Optional "move to…" mock menu (TextView)
   - Bottom bar:
       • Buttons: "Edit Name", "Share", "Delete"
   - Comments:
       • Share/Delete are visual only

G) Optional Settings/Profile (activity_settings.xml)
   - Theme toggles (Light/Dark mock), Accent picker preview chips (use accent color blocks)
   - Profile section (name, email—visual only)

-----------------------------------
🎨 RESOURCES
-----------------------------------
1) colors.xml (apply 60-30-10)
   - primary: #0E7C3A (Grocery green) — 60%
   - primaryVariant: #0B6A32
   - secondary: #F2F5F3 (cards/surfaces) — 30%
   - secondaryVariant: #E6ECE8
   - accent: #F5A524 (CTAs/highlights) — 10%
   - textPrimary: #101114
   - textSecondary: #67707A
   - border: #D9DFDA

2) strings.xml (examples)
   - app_name, search_categories, my_lists, create_from_categories
   - build_list, save_list, clear, selected_count, list_name_hint
   - share, delete, edit_name, items, quantity, add, increase, decrease
   - categories: fruits, vegetables, dairy, bakery, meat, snacks, beverages, household

3) drawables
   - Rounded card backgrounds, ripple effects
   - Placeholder category/item icons

-----------------------------------
📐 LAYOUT GUIDELINES
-----------------------------------
- Use ConstraintLayout on top-level screens; keep content within 16dp margins.
- Use 8dp spacing between sibling views; 12–16dp padding inside cards.
- For grids, show 2–3 columns depending on width (use fixed span assumption in XML).
- Item row content:
   • Left: 64dp square ImageView
   • Middle: name + unit/price (TextViews)
   • Right: quantity stepper (−, qty, +) over "Add" button
- Accessibility: set contentDescription on icons/images.

-----------------------------------
🧭 NAVIGATION PLAN (XML comments only, no code)
-----------------------------------
- From Splash → Home
- From Home:
   • Category card → Category Items (pass categoryId conceptually)
   • FAB "My Lists" → Saved Lists
- From Category Items:
   • "Build List" → List Builder
- From List Builder:
   • "Save List" → Saved Lists (new list visible)
- From Saved Lists:
   • List card → List Detail
   • FAB → Home

Add `<!-- NAV: to <ActivityName> -->` comments on clickable views (Buttons, cards, FABs). This models Intent navigation without writing code.

-----------------------------------
📂 REQUIRED FILES TO OUTPUT
-----------------------------------
/app/src/main/res/layout/
   activity_splash.xml
   activity_home.xml
   item_category.xml        (card for categories)
   activity_category_items.xml
   item_grocery.xml         (row for a grocery item)
   activity_list_builder.xml
   activity_lists.xml
   item_saved_list.xml      (row for a saved list)
   activity_list_detail.xml
   activity_settings.xml    (optional)
/app/src/main/res/values/
   colors.xml
   strings.xml
   dimens.xml               (8dp, 12dp, 16dp spacing tokens)
/app/src/main/res/drawable/
   bg_card.xml, bg_chip.xml, ripple_primary.xml, placeholder icons

Ensure every screen is fully designed in XML with realistic placeholders so screenshots can be captured for the report.
