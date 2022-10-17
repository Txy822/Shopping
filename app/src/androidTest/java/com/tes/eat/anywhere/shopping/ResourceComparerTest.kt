package com.tes.eat.anywhere.shopping

//import com.google.common.truth.Truth.assertThat

import android.content.Context
import androidx.test.core.app.ApplicationProvider



import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ResourceComparerTest {

    private  lateinit var  resourceComparer :ResourceComparer


    @Before
    fun setUp() {
        resourceComparer=ResourceComparer()

    }

    @After
    fun tearDown() {
    }

    @Test
    fun isEqual() {
    }

    @Test
    fun stringResourceSameAsGivenString_returnsTrue(){
       val  context =ApplicationProvider.getApplicationContext<Context>()
        val result =resourceComparer.isEqual(context,R.string.app_name,"Shopping")
        assertTrue(result)
    }
    @Test
    fun stringResourceDifferentAsGivenString_returnsFalse(){
        val  context =ApplicationProvider.getApplicationContext<Context>()
        val result =resourceComparer.isEqual(context,R.string.app_name,"Shopping")
        assertTrue(result)
    }
}