package com.tes.eat.anywhere.shopping

object RegistrationUtil {
    private val existingUsers =listOf("User1","user2")
/*
the input is not valid if pass/username is empty and
the user name is taken
the confirmed password is not the same
password contains less than 2 char
 */
    fun validateRegistrationInput(
        username:String,
        password:String,
        confirmedPassword:String
    ):Boolean {
        if(username.isEmpty() || password.isEmpty()){
            return false
        }
    if(username in existingUsers){
        return  false
    }
    if(password!=confirmedPassword){
        return false
    }
    if(password.count{it.isDigit()}<2){
        return false

    }

        return true
    }
}