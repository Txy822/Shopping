package com.tes.eat.anywhere.shopping.util

import com.google.common.truth.Truth.assertThat
import com.tes.eat.anywhere.shopping.util.RegistrationUtil
import org.junit.After
import org.junit.Before
import org.junit.Test

class RegistrationUtilTest {
   private  var registrationUtil: RegistrationUtil? = null

    @Before
    fun setUp() {
         registrationUtil= RegistrationUtil()
    }

    @Test
    fun `empty username returns false`(){

        val result = registrationUtil?.validateRegistrationInput(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password`(){
        val result = registrationUtil?.validateRegistrationInput(
            "user1",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }
    @Test
    fun `username already exists retuns false`(){
        val result = registrationUtil?.validateRegistrationInput(
            "user2",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun validateRegistrationInput() {
    }

    @After
    fun tearDown() {
    }

}