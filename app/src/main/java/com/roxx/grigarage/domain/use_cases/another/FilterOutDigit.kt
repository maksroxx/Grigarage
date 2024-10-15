package com.roxx.grigarage.domain.use_cases.another

class FilterOutDigit {

    operator fun invoke(text: String): String {
        return text.filter { it.isDigit() }
    }
}