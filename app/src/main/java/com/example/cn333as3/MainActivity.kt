package com.example.cn333as3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessing()


        }
    }
}


var status = true
var random: Int = Random.nextInt(1, 1000)
var count  = 0

@Composable
fun NumberGuessing() {
    val question = remember { mutableStateOf("Try to guess the number I'm thinking of from 1 - 1000!") }
    val ans = remember { mutableStateOf(TextFieldValue()) }
    val submit = remember { mutableStateOf("") }

    fun resetGame() {
        random = Random.nextInt(1, 1000)
        question.value = "Try to guess the number I'm thinking of from 1 - 1000!"
        status = true
        count = 0
    }

    fun algorithm() {
        if (status) {
            if (ans.value.text.isEmpty()) {
                question.value = "Please Enter your guess number"
            } else {
                if (ans.value.text.toInt() > random) {
                    question.value = "Hint: It's lower!"
                    count++

                } else if (ans.value.text.toInt() < random) {
                    question.value = "Hint: It's higher!"
                    count++

                } else {
                    question.value = "Congratulation! you guess $count times before it's correct"
                    status = false
                }
            }
        } else {
            resetGame()
        }


    }


    Column(
        modifier = Modifier.padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    )
    {   Text( question.value,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(50.dp)
            .fillMaxWidth().background(Color(0xFFCCFFFF))

    )
        if(status) {
            TextField(
                value = ans.value,
                onValueChange = {ans.value = it },
                singleLine = true,
                placeholder = { Text("Your guess")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

            )
        }


        Button( onClick = { algorithm() } ) {
            if(status) {
                submit.value = "Enter"
                Text(submit.value)
            } else{
                submit.value = "Play again"
                Text(submit.value)
            }

        }


    }
}
