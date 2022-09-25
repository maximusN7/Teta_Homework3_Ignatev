package com.example.teta_homework3_ignatev.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teta_homework3_ignatev.R
import kotlin.math.roundToInt

@ExperimentalComposeUiApi
@Composable
fun AdvancedScreen() {
    var isTextBlur by remember { mutableStateOf(false) }
    val textBlurAnim: Int by animateIntAsState(
        targetValue = if (isTextBlur) 8 else 0,
        animationSpec = tween(100)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .blur(radius = textBlurAnim.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Text(
            text = "Certificates",
            fontSize = 60.sp,
            color = Color.Black,
            modifier = Modifier.padding(5.dp)
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }
        val offsetXBlackAnim: Float by animateFloatAsState(
            targetValue = offsetX,
            animationSpec = tween(30, easing = FastOutSlowInEasing),
        )
        val offsetYBlackAnim: Float by animateFloatAsState(
            targetValue = offsetY,
            animationSpec = tween(30, easing = FastOutSlowInEasing),
        )
        val offsetXBlueAnim: Float by animateFloatAsState(
            targetValue = offsetX,
            animationSpec = tween(70, easing = FastOutSlowInEasing),
        )
        val offsetYBlueAnim: Float by animateFloatAsState(
            targetValue = offsetY,
            animationSpec = tween(70, easing = FastOutSlowInEasing),
        )
        val offsetXRedAnim: Float by animateFloatAsState(
            targetValue = offsetX,
            animationSpec = tween(90, easing = FastOutSlowInEasing),
        )
        val offsetYRedAnim: Float by animateFloatAsState(
            targetValue = offsetY,
            animationSpec = tween(90, easing = FastOutSlowInEasing),
        )

        var isAdditionRed by remember { mutableStateOf(false) }
        var isAdditionBlue by remember { mutableStateOf(false) }
        val additionRedAnim: Int by animateIntAsState(
            targetValue = if (isAdditionRed) 550 else 0,
            animationSpec = tween(100)
        )
        val additionBlueAnim: Int by animateIntAsState(
            targetValue = if (isAdditionBlue) 300 else 0,
            animationSpec = tween(100)
        )

        var isRotationRed by remember { mutableStateOf(true) }
        var isRotationBlue by remember { mutableStateOf(true) }
        val rotationRedAnim: Float by animateFloatAsState(
            targetValue = if (isRotationRed) 12f else 0f,
            animationSpec = tween(100)
        )
        val rotationBlueAnim: Float by animateFloatAsState(
            targetValue = if (isRotationBlue) 5f else 0f,
            animationSpec = tween(100)
        )

        var isRotationX by remember { mutableStateOf(false) }
        val rotationXAnim: Float by animateFloatAsState(
            targetValue = if (isRotationX) 3f else 0f,
            animationSpec = tween(100)
        )


        Card(
            Modifier
                .offset {
                    IntOffset(
                        offsetXRedAnim.roundToInt(),
                        offsetYRedAnim.roundToInt() - 100 - additionRedAnim
                    )
                }
                .size(width = 240.dp, height = 150.dp)
                .graphicsLayer(
                    rotationZ = rotationRedAnim,
                    rotationX = rotationXAnim,
                    alpha = 0.9f
                ),
            elevation = 4.dp,
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color.Red
        ) {}

        Card(
            Modifier
                .offset {
                    IntOffset(
                        offsetXBlueAnim.roundToInt(),
                        offsetYBlueAnim.roundToInt() - 45 - additionBlueAnim
                    )
                }
                .size(width = 260.dp, height = 160.dp)
                .graphicsLayer(
                    rotationZ = rotationBlueAnim,
                    rotationX = rotationXAnim,
                    alpha = 0.9f
                ),
            elevation = 4.dp,
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color.Blue
        ) {}

        Card(
            Modifier
                .offset { IntOffset(offsetXBlackAnim.roundToInt(), offsetYBlackAnim.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, dragAmount ->
                            change.consumeAllChanges()
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                        },
                        onDragEnd = {
                            offsetX = 0f
                            offsetY = 0f
                            isRotationRed = true
                            isRotationBlue = true
                            isRotationX = false
                            isAdditionBlue = false
                            isAdditionRed = false
                            isTextBlur = false
                        },
                        onDragStart = {
                            isRotationRed = false
                            isRotationBlue = false
                            isRotationX = true
                            isAdditionBlue = true
                            isAdditionRed = true
                            isTextBlur = true
                        })
                }
                .size(width = 280.dp, height = 170.dp)
                .graphicsLayer(
                    rotationX = rotationXAnim,
                    alpha = 0.9f
                ),
            elevation = 4.dp,
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color.Black
        ) {
            Row(modifier = Modifier.padding(15.dp)) {
                Text(
                    text = "Compose Animations",
                    fontSize = 20.sp,
                    color = Color.White,

                    )
                Spacer(modifier = Modifier.size(height = 0.dp, width = 40.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_success),
                    contentDescription = "",
                    tint = Color.Green
                )
            }

            Text(
                text = "certificate of excellence",
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 40.dp, start = 15.dp),
            )
        }
    }
}