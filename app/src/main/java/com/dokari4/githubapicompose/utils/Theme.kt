package com.dokari4.githubapicompose.utils

enum class Theme(val displayName: String, val value: Int) {
    AUTO("System Default", 0),
    DARK_MODE("Dark Mode", 1),
    LIGHT_MODE("Light Mode", 2);

    companion object {
        fun fromValue(value: Int): Theme = entries.find { it.value == value } ?: AUTO
    }
}