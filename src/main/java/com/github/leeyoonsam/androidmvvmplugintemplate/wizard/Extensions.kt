package com.github.leeyoonsam.androidmvvmplugintemplate.wizard

fun String.toCamelToSnakeCase(): String {
    val snakeCase = StringBuilder()
    for(character in this) {
        if(character.isUpperCase()) {
            snakeCase.append("_${character.lowercaseChar()}")
        } else {
            snakeCase.append(character)
        }
    }
    return snakeCase.removePrefix("_").toString()
}