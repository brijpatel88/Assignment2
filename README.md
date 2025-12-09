# ☕ Coffee Ordering App — Tim Hortons Style
### Android · Kotlin · Jetpack Compose · Material 3

<p align="center">
  <img src="https://img.shields.io/badge/Android-34+-3DDC84?logo=android&logoColor=white&style=for-the-badge" />
  <img src="https://img.shields.io/badge/Kotlin-1.9-7F52FF?logo=kotlin&logoColor=white&style=for-the-badge" />
  <img src="https://img.shields.io/badge/Jetpack%20Compose-Material%203-4285F4?logo=jetpackcompose&logoColor=white&style=for-the-badge" />
  <img src="https://img.shields.io/badge/Platform-Android%20Studio%20Hedgehog-blue?style=for-the-badge" />
</p>

## 🌟 Overview
A complete mobile coffee-ordering experience designed using Kotlin + Jetpack Compose.
Users can browse drinks, select sizes, add to cart, review orders, and place payment — inspired by Tim Hortons UX.

This app was created as part of Mobile App Development — Assignment 2.

---

# 🎥 Live Demo (GIF)
Upload your GIF demo to `screenshots/demo.gif` then replace this:

`![App Demo](screenshots/demo.gif)`

---

# 🖼 Screenshots Gallery
Replace the placeholder images after uploading your screenshots.

| Intro | Home | Drink List |
|-------|-------|------------|
| ![](screenshots/intro.png) | ![](screenshots/home.png) | ![](screenshots/drink_list.png) |

| Drink Details | Cart | Review |
|----------------|------|--------|
| ![](screenshots/details.png) | ![](screenshots/cart.png) | ![](screenshots/review.png) |

| Payment | Thank You |
|---------|-----------|
| ![](screenshots/payment.png) | ![](screenshots/thankyou.png) |

---

# 🚀 Features
- Browse Coffee / Latte / Tea categories  
- Modern size selector (S/M/L)  
- Add to cart  
- Update quantity  
- Remove items  
- Subtotal, Tax, Total calculations  
- Review Order screen  
- Payment screen  
- Thank You screen  
- Cart clears after payment  

---

# 🧱 Tech Stack
- Kotlin  
- Jetpack Compose  
- Material 3  
- Navigation Compose  
- State-driven UI  
- mutableStateListOf cart manager  

---

# 📂 Project Structure

app/
└── java/com.assignment2.coffeeapp/
├── data/
│     ├── Drink.kt
│     ├── DrinkSize.kt
│     ├── DrinkType.kt
│     ├── CartItem.kt
│     └── CartManager.kt
│
├── ui/
│     ├── intro/IntroScreen.kt
│     ├── home/HomeScreen.kt
│     ├── drinks/DrinkListScreen.kt
│     ├── details/DrinkDetailsScreen.kt
│     ├── cart/CartScreen.kt
│     ├── review/ReviewOrderScreen.kt
│     ├── payment/PaymentScreen.kt
│     └── thankyou/ThankYouScreen.kt
│
└── MainActivity.kt
---

# ▶️ Running the App

Clone the repo:
git clone https://github.com/brijpatel88/Assignment2.git
Open in **Android Studio** → Run on emulator or device.

---

# 📜 MIT License
Placed in the `LICENSE` file.

---

# 👤 Author
**Brijesh Patel**  
GitHub: https://github.com/brijpatel88
