![Github Header](https://github.com/ragvax/Dictionary/blob/master/assets/DictionaryHeader.jpg?raw=true)

# Dictionary 📖
[![build](https://github.com/ragvax/Dictionary/actions/workflows/build.yaml/badge.svg)](https://github.com/ragvax/Dictionary/actions)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?labelColor=373e45&logo=android&style=flat)](https://android-arsenal.com/api?level=21)
[![github profile](https://img.shields.io/badge/Github-ragvax-4a88ea?labelColor=373e45&style=flat&logo=github)](https://github.com/ragvax)

Dictionary is a small online dictionary application to look up the meanings and definitions of words entered by users. It simply fetches data from the remote API created by [Meet Developer](https://github.com/meetDeveloper) and displays it to the users. This application is built based on modern Android development tech-stacks recommended by the Android team with MVVM and Single Activity Architecture. Currently, this app only supports English.

## Screenshots 📷
<img src="https://github.com/ragvax/Dictionary/blob/master/assets/home_day.png" width="24%">  <img src="https://github.com/ragvax/Dictionary/blob/master/assets/definition_day.png" width="24%">  <img src="https://github.com/ragvax/Dictionary/blob/master/assets/home_night.png" width="24%">  <img src="https://github.com/ragvax/Dictionary/blob/master/assets/definition_night.png" width="24%">

## Built With 🛠
* Minimum SDK level 21
* Kotlin
* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
  * [AppCompat][1] - Degrade gracefully on older versions of Android.
  * [Android KTX][2] - Write more concise, idiomatic Kotlin code.
* [Architecture][3] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [Lifecycles][4] - Create a UI that automatically responds to lifecycle events.
  * [Navigation][5] - Handle everything needed for in-app navigation.
  * [Room][5] - Access your app's SQLite database with in-app objects and compile-time checks.
  * [ViewModel][6] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
    asynchronous tasks for optimal execution.
* Third party and miscellaneous libraries
  * [Hilt][7]: for [dependency injection][8]
  * [Kotlin Coroutines][9] for managing background threads with simplified code and reducing needs for callbacks

[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat
[2]: https://developer.android.com/kotlin/ktx
[3]: https://developer.android.com/jetpack/arch/
[4]: https://developer.android.com/topic/libraries/architecture/lifecycle
[5]: https://developer.android.com/topic/libraries/architecture/navigation/
[5]: https://developer.android.com/topic/libraries/architecture/room
[6]: https://developer.android.com/topic/libraries/architecture/viewmodel
[7]: https://developer.android.com/training/dependency-injection/hilt-android
[8]: https://developer.android.com/training/dependency-injection
[9]: https://kotlinlang.org/docs/reference/coroutines-overview.html

## License 🔖
```
Copyright 2021 Rizki Fajar Maulian

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```