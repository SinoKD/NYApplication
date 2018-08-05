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

First view list collection of most viewed articles for 7 days by default.
We can change the value by using menu option(1,7,30).
when we click the artilce then load the details screen.
At the end of the screen there is 'Read More' link to read more about the article in web browswer or in Newyork Times App.
 

## Architecture Pattern
The application make use of MPV architecture pattern.

## License

Please see the [LICENSE.md](https://github.com/SinoKD/NyApplication/blob/master/LICENSE) file for details
