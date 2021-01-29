package com.appcraft.testapp.utils

enum class MainTab {
    WEB,
    SAVED;

    companion object {
        fun getValueFromString(string: String?): MainTab {
            return values().firstOrNull { it.toString() == string } ?: WEB
        }
    }
}