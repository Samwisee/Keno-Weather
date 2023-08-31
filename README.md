# TLCKeno Weather App

## Overview

This is a weather application built using Android's modern development tools for a coding interview. The app fetches weather data from the Open Weather API and displays it.

## Features

- List of cities with current weather information
- Detailed weather data for selected city
- Composable UI
- Material Design
- MVVM Architecture
- Unit Tests

## Technologies Used

- [Kotlin](https://kotlinlang.org/)
- [Android Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Retrofit for API calls](https://square.github.io/retrofit/)
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Koin for Dependency Injection](https://insert-koin.io/)
- [JUnit 4 for unit testing](https://junit.org/junit4/)

## Getting Started

### Requirements

- Android Studio Arctic Fox or later
- JDK 11 or later
- Android device or emulator with Android API level 34 or higher

### Installation

1. Clone the repository

```
git clone https://github.com/Samwisee/Keno-Weather.git
```

2. Open the project in Android Studio

3. Sync the Gradle files

4. Run the app on an emulator or actual Android device

## Running Tests

To run the existing unit tests, follow these steps:

1. Open Android Studio
2. Navigate to the `Test` directory
3. Right-click on the directory and select `Run Tests`

## Code Structure

- `data/` - contains data models and API service interface
- `domain/` - contains repository implementations and domain logic
- `present/` - contains ViewModel and UI components
- `util/` - utility classes

## Acknowledgments

- OpenWeather API for providing weather data
- Android documentation and community for invaluable resources
