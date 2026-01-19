
# ☕ CoffeeApp — Modern Coffee Ordering App

![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blueviolet?logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-4285F4?logo=android)
![Android](https://img.shields.io/badge/Android-API%2026%2B-3DDC84?logo=android)
![Stripe](https://img.shields.io/badge/Stripe-Payments-635BFF?logo=stripe)
![Status](https://img.shields.io/badge/Status-Completed-success)
![License](https://img.shields.io/badge/License-Proprietary-red)

A **modern Android coffee ordering application** built with **Jetpack Compose**.  
Users can browse drinks, customize orders, manage favorites, place secure payments using **Stripe**, and view order history with re‑order support.

---

## 🎥 Video Presentation

▶️ **Watch Demo Video:**  
https://youtube.com/shorts/lYteEbKk_TE

---

## 📱 App Screenshots

| Home | Menu | Details |
|------|------|---------|
| ![](C1.jpeg) | ![](C2.jpeg) | ![](C3.jpeg) |

| Cart | Payment | Stripe |
|------|--------|--------|
| ![](C4.jpeg) | ![](C5.jpeg) | ![](C6.jpeg) |

| Thank You | Favorites | Order History |
|-----------|-----------|---------------|
| ![](C7.jpeg) | ![](C8.jpeg) | ![](C9.jpeg) |

| Profile |
|---------|
| ![](C10.jpeg) |

---

## ✨ Features

- 🏠 **Home Dashboard**
  - Browse coffee, latte, and tea categories
- ☕ **Drink Customization**
  - Size, milk type, sugar level, and extras
- ❤️ **Favorites**
  - Save drinks for quick re‑order
- 🛒 **Smart Cart**
  - Quantity control, tax calculation, total summary
- 💳 **Stripe Payments (Test Mode)**
  - Secure checkout using Stripe PaymentSheet
- 📜 **Order History**
  - View previous orders
  - Re‑order with one tap
- 🧾 **Bill Screen**
  - Clean, readable receipt UI
- 👤 **Profile Screen**
  - User info and demo settings
- 🎨 **Modern UI**
  - Material 3, gradients, rounded cards, clean spacing

---

## 🧠 Architecture & Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose (Material 3)
- **Navigation:** Navigation Compose
- **State:** State hoisting + managers
- **Payments:** Stripe Android SDK (PaymentSheet)
- **Architecture:** UI + Data Managers (Assignment‑friendly)
- **Min SDK:** 26

---

## 🗂️ Project Structure

```
com.assignment2.coffeeapp
│
├── data/
│   ├── Drink.kt
│   ├── CartManager.kt
│   ├── OrderHistoryManager.kt
│   └── enums (Size, Milk, Sugar, Extras)
│
├── ui/
│   ├── home/
│   ├── drinks/
│   ├── details/
│   ├── cart/
│   ├── payment/
│   ├── thankyou/
│   ├── history/
│   ├── favorites/
│   ├── bill/
│   └── profile/
│
├── navigation/
│   ├── MainScaffold.kt
│   └── BottomNavItem.kt
│
└── viewmodel/
    └── PaymentViewModel.kt
```

---

## ⚙️ Setup Instructions

1. Clone the repository
   ```bash
   git clone https://github.com/brijpatel88/Assignment2.git
   ```

2. Open in **Android Studio**
3. Sync Gradle
4. Run on emulator or physical device (API 26+)
5. For payment testing, use Stripe test card:
   ```
   4242 4242 4242 4242
   Any future date | Any CVC
   ```

---

## 📝 Notes

- Payments are in **Stripe Test Mode**
- Data persistence is **in‑memory** (assignment scope)
- Designed for **educational and portfolio use**

---

## 📄 License

```
Copyright (c) 2026 Brijesh Patel

All rights reserved.

This software and associated source code (the "Software") are proprietary
and confidential. Unauthorized copying, modification, distribution,
public display, or commercial use of this Software, in whole or in part,
is strictly prohibited without prior written permission from the author.

This repository is provided solely for:
- Educational purposes
- Portfolio demonstration
- Code review and learning reference

NO LICENSE is granted for commercial use, redistribution, or derivative works.
```

---

☕ *Thank you for reviewing CoffeeApp!*  
If this were a real café — your order would already be ready 😄
