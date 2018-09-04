# Android API Wrapper

[![Release](https://jitpack.io/v/2CodersStudio/android-api-wrapper.svg)](https://jitpack.io/#2CodersStudio/android-api-wrapper)
#### A little wrapper made to simplify the API setup on new projects
This wrapper uses [Fuel](https://github.com/kittinunf/Fuel).

### Installation
On your root build.gradle:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the dependencies:
```
	dependencies {
	        implementation 'com.github.2CodersStudio:android-api-wrapper:1.3'
	        implementation 'com.github.kittinunf.fuel:fuel:1.15.0'
            implementation 'com.github.kittinunf.fuel:fuel-android:1.15.0'
            implementation 'com.github.kittinunf.fuel:fuel-livedata:1.15.0'
            implementation 'com.github.kittinunf.fuel:fuel-rxjava:1.15.0'
            implementation 'com.github.kittinunf.fuel:fuel-gson:1.15.0'
            implementation "com.fasterxml.jackson.module:jackson-module-kotlin:2.9.3"
            implementation 'com.github.kittinunf.fuel:fuel-moshi:1.15.0'
	}
```

### Initialization


```
API.initManager(basePath = "https://jsonplaceholder.typicode.com", baseHeaders = mapOf("Content-Type" to "application/json"), debug = true)
```



### Configuration
- You can enable the DEBUG option, passing the the parameter debug to true throw the initialization constructor , it will print a debug log with the request/response.

- The default timeout is set to 30000, but you can modify it by:


```
API.timeout = 20000
```
- You can also modify the DEBUG_KEY by:
```
API.LOG_DEBUG_KEY = "Your Debug Key
```

### Usage

Congrats! You can now do request easily:
```
      API.request(
                endPoint = "todos",
                method = API.RequestMethod.GET,
                onSuccess = { response ->
                    println("DEBUG RESPONSE $response")
                },
                onFailure = { resultResponse, retryAction, statusCode ->
                    println("DEBUG ERROR $resultResponse $statusCode")
                }
        )
```

