# Dictionary

A simple dictionary app that uses the API created by [Meet Developer](https://github.com/meetDeveloper). This dictionary app uses that API to show definitions for the words entered by the user. This app currently only supports English language.

## Screenshot
<img src="https://github.com/ragvax/Dictionary/blob/master/assets/home_day.png" width="225"> <img src="https://github.com/ragvax/Dictionary/blob/master/assets/definition_day.png" width="225">
<img src="https://github.com/ragvax/Dictionary/blob/master/assets/home_night.png" width="225"> <img src="https://github.com/ragvax/Dictionary/blob/master/assets/definition_night.png" width="225">

## Libraries Used
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