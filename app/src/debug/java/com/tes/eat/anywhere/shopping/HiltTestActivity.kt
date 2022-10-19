package com.tes.eat.anywhere.shopping

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

//attach fragments for test purposes
//we added manifest file in this debug because as it is test activity we don't want to add_  this activity to actual app manifest
@AndroidEntryPoint
class HiltTestActivity :AppCompatActivity()