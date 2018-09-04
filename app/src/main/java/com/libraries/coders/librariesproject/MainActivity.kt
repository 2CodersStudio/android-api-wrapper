package com.libraries.coders.librariesproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.libraries.coders.comcodersapiwrapper.API

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        API.initManager(basePath = "https://jsonplaceholder.typicode.com", baseHeaders = mapOf("Content-Type" to "application/json"), debug = true)
        API.LOG_DEBUG_KEY = "Example"
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
    }
}
