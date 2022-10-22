package com.tes.eat.anywhere.shopping

import com.google.common.truth.Truth.assertThat
import com.tes.eat.anywhere.shopping.util.RegistrationUtil
import org.junit.After
import org.junit.Before
import org.junit.Test

class RegistrationUtilTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `empty username returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "user1",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }
    @Test
    fun `username already exists retuns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "user2",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    //empty password
    //password was repeated incorrectly
    //password contains less than 2 digits
    @Test
    fun validateRegistrationInput() {
    }
}