package com.example.test3

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.test3.ui.theme.CalckResult


 class VModel (
    ): ViewModel(){
     var One by mutableStateOf("")

     var rem by mutableStateOf(true)

     var Tou by mutableStateOf("")

     var sign by mutableStateOf("")
     fun setOne (result: CalckResult){
   One =result.One

}
     fun setTou (result: CalckResult){
         Tou =result.Tou

     }
 }
