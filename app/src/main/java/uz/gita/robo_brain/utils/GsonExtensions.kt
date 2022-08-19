package uz.gita.robo_brain.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// Created by Jamshid Isoqov an 8/19/2022

val gson = Gson()

inline fun <reified T> String.parseTo(): T {
    val type = object : TypeToken<T>() {}.type
    return gson.fromJson<T>(this, type)
}

fun <T> T.toJson(): String {
    return gson.toJson(this)
}