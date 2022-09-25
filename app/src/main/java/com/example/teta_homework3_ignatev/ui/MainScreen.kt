package com.example.teta_homework3_ignatev.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen() {
    var screenStateBasic by remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (screenStateBasic) {
            BasicScreen()
        } else {
            AdvancedScreen()
        }

        Button(
            onClick = {
                screenStateBasic = !screenStateBasic
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black
            ),
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Change Screen",
                color = Color.White
            )
        }
    }
}