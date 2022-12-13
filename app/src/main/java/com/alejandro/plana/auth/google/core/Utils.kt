package com.alejandro.plana.auth.google.core

import android.util.Log
import com.alejandro.plana.auth.google.core.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception) {
            Log.e(TAG, e.message ?: e.toString())
        }
    }
}