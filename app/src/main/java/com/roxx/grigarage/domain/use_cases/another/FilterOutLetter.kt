package com.roxx.grigarage.domain.use_cases.another

class FilterOutLetter {

    operator fun invoke(text: String): String {
        return text.filter { it.isLetter() }
    }
}