package com.example.shapovalov

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment

inline fun <reified T : Parcelable> Bundle.getParcelableCompat(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

inline fun <reified T : Parcelable> Fragment.getParcelableCompat(key: String): T? {
    return arguments?.getParcelableCompat(key)
}
