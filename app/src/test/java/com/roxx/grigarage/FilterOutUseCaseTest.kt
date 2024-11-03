package com.roxx.grigarage

import com.roxx.grigarage.domain.use_cases.another.FilterOutDigit
import com.roxx.grigarage.domain.use_cases.another.FilterOutLetter
import org.junit.Assert.assertEquals
import org.junit.Test

class FilterOutUseCaseTest {

    private val filterOutDigit = FilterOutDigit()
    private val filterOutLetter = FilterOutLetter()

    @Test
    fun `should return only digits from the input string`() {
        val input = "abc123def456"
        val expected = "123456"
        val result = filterOutDigit(input)
        assertEquals(expected, result)
    }

    @Test
    fun `should return empty string when no digits are present`() {
        val input = "abcdef"
        val expected = ""
        val result = filterOutDigit(input)
        assertEquals(expected, result)
    }

    @Test
    fun `should return empty string when input is empty for digit use case`() {
        val input = ""
        val expected = ""
        val result = filterOutDigit(input)
        assertEquals(expected, result)
    }

    @Test
    fun `should return only digits when input consists of digits only`() {
        val input = "123456"
        val expected = "123456"
        val result = filterOutDigit(input)
        assertEquals(expected, result)
    }

    @Test
    fun `should return only letters from the input string`() {
        val input = "abc123def456"
        val expected = "abcdef"
        val result = filterOutLetter(input)
        assertEquals(expected, result)
    }

    @Test
    fun `should return empty string when no letters are present`() {
        val input = "123456"
        val expected = ""
        val result = filterOutLetter(input)
        assertEquals(expected, result)
    }

    @Test
    fun `should return empty string when input is empty for letter use case`() {
        val input = ""
        val expected = ""
        val result = filterOutLetter(input)
        assertEquals(expected, result)
    }

    @Test
    fun `should return only letters when input consists of letters only`() {
        val input = "abcdef"
        val expected = "abcdef"
        val result = filterOutLetter(input)
        assertEquals(expected, result)
    }
}