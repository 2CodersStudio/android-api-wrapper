package com.libraries.coders.comcodersapiwrapper

import android.app.Activity
import android.util.Log


/**
 * Created by Alvaro on 08/06/2018.
 */

import com.github.kittinunf.fuel.*
import com.github.kittinunf.fuel.core.*
import com.github.kittinunf.fuel.core.interceptors.cUrlLoggingRequestInterceptor
import com.github.kittinunf.result.Result
import com.google.gson.Gson


typealias ParamItem = Pair<String, Any?>

class API {
    enum class RequestMethod {
        GET, POST, PUT, DELETE, PATCH
    }

    companion object {
        var timeout = 30000
        private val mapper = Gson()
        var LOG_DEBUG_KEY = "API DEBUG KEY "

        fun initManager(basePath: String, debug: Boolean = false, baseHeaders: Map<String, String>) {
            FuelManager.instance.basePath = basePath
            FuelManager.instance.baseHeaders = baseHeaders
            if (debug) {
                logAPI()
            }
        }

        private fun logAPI() {

            FuelManager.instance.addRequestInterceptor(cUrlLoggingRequestInterceptor())
            FuelManager.instance.addResponseInterceptor { next: (Request, Response) -> Response ->
                { req: Request, res: Response ->
                    Log.d(LOG_DEBUG_KEY, "REQUEST COMPLETED: " + req.toString())
                    Log.d(LOG_DEBUG_KEY, "RESPONSE: " + res.toString())
                    next(req, res)
                }
            }
        }

        fun request(endPoint: String
                    , onSuccess: (resultResponse: Result<String, FuelError>) -> Unit = {}
                    , onFailure: (resultResponse: Result<String, FuelError>, context: Activity, retryAction: () -> Unit?, statusCode: Int) -> Unit
                    , params: List<Pair<String, Any?>>? = null
                    , body: Any? = null
                    , showDialog: Boolean = false
                    , context: Activity
                    , method: RequestMethod) {

            val request = when (method) {
                Method.GET -> endPoint.httpGet(params).timeout(timeout).timeoutRead(timeout)
                Method.POST -> endPoint.httpPost(params).timeout(timeout).timeoutRead(timeout)
                Method.PUT -> endPoint.httpPut(params).timeout(timeout).timeoutRead(timeout)
                Method.DELETE -> endPoint.httpDelete(params).timeout(timeout).timeoutRead(timeout)
                Method.PATCH -> endPoint.httpPatch(params).timeout(timeout).timeoutRead(timeout)
                else -> null
            }

            request?.body(mapper.toJson(body))?.responseString { _, response, result ->
                when (result) {

                    is Result.Failure -> {
                        onFailure(result, context, { request(endPoint, onSuccess, onFailure, params, body, showDialog, context, method) }, response.statusCode)
                    }
                    is Result.Success -> {
                        manageSuccess(onSuccess, result, context)
                    }
                }
            }


        }


        private fun manageSuccess(onSuccess: (resultResponse: Result<String, FuelError>) -> Unit, result: Result<String, FuelError>, context: Activity) {
            onSuccess(result)
        }


    }
}