package com.harryawoodworth.multiweather.utils

/**
 * Represent the state of the Network Call being made
 * @param: uiStatus is the current state of the UI
 * @param: data is the TODO
 * @param: errorMessage is the message returned in the case of an error
 * We use <out T> to tell the compiler that T is only produced by this class, not consumed
 * This is called declaration-site variance: https://kotlinlang.org/docs/generics.html#declaration-site-variance
 */
data class NetworkCallStatus<out T>(val uiStatus: UIStatus, val data: T?, val errorMessage: String) {
    companion object {
        fun <T> success(data: T?): NetworkCallStatus<T> {
            return NetworkCallStatus(UIStatus.SUCCESS, data, "")
        }
        fun <T> error(message: String, data: T?): NetworkCallStatus<T> {
            return NetworkCallStatus(UIStatus.ERROR, data, message)
        }
        fun <T> loading(data: T?): NetworkCallStatus<T> {
            return NetworkCallStatus(UIStatus.LOADING, data, "")
        }
    }
}
