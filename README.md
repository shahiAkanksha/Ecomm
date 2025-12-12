**1. Introduction**

This project as implementation of a modern Android application using Jetpack Compose, Preferences, DataStore Preferences and Navigation Compose. The app shows a listscreen with tiles of tasks where thhe tiles contain title, description, and completion status that stays persistent with app restarts. Click on any tile navigates to a detail screen where users can toggle the task status. The state for completion status persists even after app restarts through Preferences and DataStore. The solution follows a clean separation of concerns, scalability, and modern best practices.

**2. How to Run the Application**
 --Clone/Download the project from the repository.
 --Open the project in Android Studio (Hedgehog or later).
 --Allow Android Studio to auto-sync Gradle and resolve dependencies.
 --Ensure an emulator (API 23+) or real device is available.
 --Click Run → Run 'app' or use the RUN button.
 --The app should build successfully and launch to the task list screen.
Build is fully compatible with AGP 8.7.0, Kotlin 2.x, and Compose 2024.11 BOM.

**3. Architecture Used – MVVM**
The application follows the MVVM pattern, ensuring a structured and maintainable codebase.

**a)MVVM Layers**
View (UI Layer – Jetpack Compose)
Displays lists, tiles, and detail screens.
Changes only to commpletion status updates from ViewModel.
Fully declarative and lifecycle-aware.

**b)ViewModel (Business Logic Layer)**
This contains state management and UI logic.
Using immutable StateFlow to the UI.
Also,Survives configuration changes.

**4. Why This Architecture Was Chosen**
a) MVVM fits perfectly suitable with Jetpack Component and Jetpac Compose. Also this is a Google recommended approach for scalability in Android apps.
b) There happens to be a separation of responsibilities and tasks to be done such as, UI logic stays in ViewModel, Data persistence stays in Preferences. This ensures that each part is testable. 
c) Testing can be done more robustly as viewModels can be unit tested without UI frameworks and Repository interfaces allow mocking Room/DataStore.
d) This structure allows easier onboarding of new developers, clearer evolution of features, plug-and-play data layer if network APIs are introduced in future
e) Using Preferences + DataStore ensures data persists across app sessions, even after killing the app withhelp of viewmodel

**5. Features Implemente**
 --Task list screen with tiles
 --Task status persistence (Completed/Pending)
 --Detail screen with toggle button
 --Filter tabs for Completed / Pending / All
 --DataStore Preferences for filter persistence
 --Navigation Compose for screen transitions
 --All components follow SOLID principles.
 
**6. What I Would Improve With One More Day**
 a) I would add Automated Testing for ViewModel unit tests & Repository tests with Fake DAO
 b) Make it some base sealed classes for Ui states to be handled when API is added to the samed concept by adding **states as Loading, Success, Error** to sealed classes to improve stability and handle failure cases.
 c) Would do better with UX & UI Polish such as: Animations for list updates Improved spacing, typography, and icons Empty-state screens for no tasks
 d) Would add a Sync Support (If API introduced later) with Layered architecture allows quick addition of Remote API.

The project showcases a clean, modular, and scalable Android application built using modern tools and best practices. The MVVM + Repository architecture ensures clarity, testability, and future expansion. With an additional day, enhancements in testing, UI polish, and error handling would further improve robustness and user experience.
