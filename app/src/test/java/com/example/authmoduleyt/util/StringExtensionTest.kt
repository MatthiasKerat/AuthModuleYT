package com.example.authmoduleyt.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StringExtensionTest {

    @Test
    fun `test string contains no number returns false when check for it`(){
        val result = "NoNumber".containsNumber()
        assertThat(result).isFalse()
    }
    @Test
    fun `test string contains a number returns true when check for it`(){
        val result = "No5Number".containsNumber()
        assertThat(result).isTrue()
    }

    @Test
    fun `test string contains no upper case returns false when check for it`(){
        val result = "number".containsUpperCase()
        assertThat(result).isFalse()
    }

    @Test
    fun `test string contains an upper case returns true when check for it`(){
        val result = "No5Number".containsUpperCase()
        assertThat(result).isTrue()
    }

    @Test
    fun `test string contains no special char returns false when check for it`(){
        val result = "nsdumber".containsSpecialChar()
        assertThat(result).isFalse()
    }

    @Test
    fun `test string contains a special char returns true when check for it`(){
        val result = "nsdumSbe!r".containsSpecialChar()
        assertThat(result).isTrue()
    }




}