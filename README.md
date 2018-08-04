# NyApplication

 NyApplication is an android app that allows a user to view the list most viewed articles by NYTimes.com
readers. When we tap on a item, the detailes are loaded (a typical master/detail app) . We'll be using the most viewed section of this API. 


**Base URI**
`http://api.nytimes.com/svc/mostpopular/{version}/mostviewed}`

**HTTP method**

GET

**Response formats**

JSON (`.json`, default)

Prerequisites
--------------

- minSdkVersion 21
- targetSdkVersion 28
- Latest Android Build Tools
- Android Support Repository
- Dagger (Depedency Injection)
- Retrofit
- RxAndroid

##Getting started
---------------

This sample uses the Gradle build system.

Download the samples by cloning this repository or downloading an archived snapshot. (See the options at the top of the page.)
In Android Studio, create a new project and choose the "Import non-Android Studio project" or "Import Project" option.
Generate a API key form [Newyork Times Developers](https://developer.nytimes.com/signup) and replace your key with API_KEY feild in AppConstants.java
Run the application with connected device or emulator

## Architecture Pattern
The application make use of MPV architecture pattern.
