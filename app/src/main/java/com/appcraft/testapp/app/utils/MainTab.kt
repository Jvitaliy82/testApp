package com.appcraft.testapp.app.utils

enum class MainTab {
    WEB,
    SAVED;

    companion object {
        fun getValueFromString(string: String?): MainTab {
            return values().firstOrNull { it.toString() == string } ?: WEB
        }
    }
}