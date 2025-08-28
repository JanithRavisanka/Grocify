# 🛒 Grocify - Smart Grocery Management App

[![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Material Design](https://img.shields.io/badge/Material--Design-757575?style=for-the-badge&logo=material-design&logoColor=white)](https://material.io/design)

> **Your personal grocery shopping companion** - Organize, plan, and manage your shopping experience with ease.

## ✨ **Features**

### 🛍️ **Smart Grocery Management**
- **Category-based organization** - Group items by type (vegetables, fruits, dairy, etc.)
- **Visual item display** - Custom icons for each grocery item
- **Quick add to cart** - Streamlined shopping experience
- **Search functionality** - Find items quickly

### 📋 **Shopping Lists**
- **Multiple lists** - Create separate lists for different purposes
- **Item management** - Add, remove, and organize items
- **Progress tracking** - Mark items as purchased
- **List sharing** - Collaborate with family members

### 👤 **User Experience**
- **Secure authentication** - Sign up, sign in, and guest mode
- **Profile management** - Customize your experience
- **Preferences** - Tailor the app to your needs
- **Settings** - App configuration and data management

### 🎨 **Modern Design**
- **Material Design 3** - Latest Android design guidelines
- **Responsive layout** - Works on all screen sizes
- **Dark/Light themes** - Choose your preferred appearance
- **Smooth animations** - Polished user experience

## 🚀 **Quick Start**

### **Prerequisites**
- Android Studio Arctic Fox or later
- Android SDK API 24+ (Android 7.0)
- Kotlin 1.8+
- Gradle 7.0+

### **Installation**

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/grocify.git
   cd grocify
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the Grocify folder
   - Click "OK"

3. **Sync and build**
   ```bash
   ./gradlew assembleDebug
   ```

4. **Run on device**
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio

## 📱 **Screenshots**

| Home Screen | Shopping Lists | User Profile | Settings |
|-------------|----------------|--------------|----------|
| ![Home](screenshots/home.png) | ![Lists](screenshots/lists.png) | ![Profile](screenshots/profile.png) | ![Settings](screenshots/settings.png) |

## 🏗️ **Architecture**

### **Technology Stack**
- **Language**: Kotlin
- **UI Framework**: Material Design Components
- **Architecture**: Activity-based with BaseNavigationActivity
- **Data Storage**: SharedPreferences
- **Build System**: Gradle with Kotlin DSL

### **Project Structure**
```
app/src/main/
├── java/com/example/grocify/
│   ├── Activities/           # All app screens
│   ├── BaseNavigationActivity.kt  # Navigation base class
│   └── Utils/               # Helper classes
├── res/
│   ├── layout/              # UI layouts
│   ├── drawable/            # Icons & graphics
│   ├── values/              # Resources
│   └── menu/                # Navigation menus
└── AndroidManifest.xml      # App configuration
```

### **Key Components**
- **BaseNavigationActivity** - Centralized navigation logic
- **Authentication System** - Secure user management
- **Bottom Navigation** - Consistent app navigation
- **Custom Icons** - Visual grocery item representation

## 🔧 **Configuration**

### **Build Configuration**
```kotlin
android {
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.grocify"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }
}
```

### **Dependencies**
```kotlin
dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}
```

## 📖 **Usage Guide**

### **Getting Started**
1. **Launch the app** - Splash screen with authentication check
2. **Sign in or create account** - Secure user authentication
3. **Navigate with bottom tabs** - Groceries, Lists, Profile, Settings
4. **Add items to cart** - Quick shopping list creation
5. **Manage your lists** - Organize and track purchases

### **Navigation**
- **🛒 Groceries** - Main shopping interface
- **📋 Lists** - Shopping list management
- **👤 Profile** - User settings and preferences
- **⚙️ Settings** - App configuration

### **Features**
- **Guest Mode** - Try the app without signing up
- **Remember Me** - Stay logged in between sessions
- **Profile Customization** - Personalize your experience
- **Data Management** - Export, import, or clear your data

## 🧪 **Testing**

### **Running Tests**
```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest

# All tests
./gradlew check
```

### **Test Coverage**
- **Unit Tests** - Individual component testing
- **Integration Tests** - Feature workflow testing
- **UI Tests** - User interface validation

## 🚀 **Deployment**

### **Release Build**
```bash
./gradlew assembleRelease
```

### **Google Play Store**
1. **Generate signed APK/AAB**
2. **Upload to Google Play Console**
3. **Configure store listing**
4. **Submit for review**

## 🤝 **Contributing**

We welcome contributions! Please see our [Contributing Guide](CONTRIBUTING.md) for details.

### **Development Setup**
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

### **Code Style**
- Follow Kotlin conventions
- Use descriptive names
- Add comments for complex logic
- Write tests for new features

## 📚 **Documentation**

- **[Project Documentation](PROJECT_DOCUMENTATION.md)** - Comprehensive technical documentation
- **[Image Setup Guide](IMAGE_SETUP_README.md)** - How to add custom images
- **[API Reference](docs/API.md)** - Code documentation
- **[Design Guidelines](docs/DESIGN.md)** - UI/UX standards

## 🐛 **Troubleshooting**

### **Common Issues**

#### **Build Problems**
```bash
# Clean project
./gradlew clean

# Invalidate caches in Android Studio
File → Invalidate Caches and Restart
```

#### **Runtime Issues**
- Check logcat for error messages
- Verify device compatibility (API 24+)
- Test on different screen sizes

#### **Image Display Issues**
- Verify drawable resources exist
- Check ImageView properties
- Test on different densities

### **Getting Help**
- Check existing [Issues](../../issues)
- Create a new issue with details
- Include device information and logs

## 📄 **License**

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 **Acknowledgments**

- **Material Design** - UI framework and guidelines
- **AndroidX** - Support libraries
- **Kotlin** - Programming language
- **Android Community** - Inspiration and support

## 📞 **Contact**

- **Project**: [Grocify Repository](../../)
- **Issues**: [GitHub Issues](../../issues)
- **Discussions**: [GitHub Discussions](../../discussions)
- **Email**: [your-email@example.com]

## 🎯 **Roadmap**

### **Version 1.1** (Q2 2024)
- [ ] Cloud synchronization
- [ ] Barcode scanning
- [ ] Price comparison

### **Version 1.2** (Q3 2024)
- [ ] Recipe integration
- [ ] Meal planning
- [ ] Social features

### **Version 2.0** (Q4 2024)
- [ ] Offline mode
- [ ] Advanced analytics
- [ ] Multi-language support

---

<div align="center">

**Made with ❤️ for the Android community**

[![GitHub stars](https://img.shields.io/github/stars/yourusername/grocify?style=social)](../../stargazers)
[![GitHub forks](https://img.shields.io/github/forks/yourusername/grocify?style=social)](../../network/members)
[![GitHub issues](https://img.shields.io/github/issues/yourusername/grocify)](../../issues)
[![GitHub pull requests](https://img.shields.io/github/issues-pr/yourusername/grocify)](../../pulls)

</div>
