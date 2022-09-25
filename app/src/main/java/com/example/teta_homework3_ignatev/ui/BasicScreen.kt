package com.example.teta_homework3_ignatev.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teta_homework3_ignatev.R
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasicScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SwipeableSample()
        //GreenButton()
        //SwipeButton()
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableSample() {
    val width = 350.dp
    val buttonSize = 44.dp

    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { width.toPx() - buttonSize.toPx() - 5.dp.toPx() }
    val anchors = mapOf(3f to 0, sizePx to 1)
    val progress = derivedStateOf {
        if (swipeableState.offset.value == 0f) 0f else swipeableState.offset.value / sizePx
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Color.Green, shape = CircleShape)
            .size(width = 350.dp, height = 50.dp)
            .alpha(1f - progress.value)
            .layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                val currentHeight = placeable.height
                val currentWidth = placeable.width
                val newDiameter = maxOf(currentHeight, currentWidth)
                layout(newDiameter, newDiameter) {
                    placeable.placeRelative(
                        (newDiameter - currentWidth) / 2 + 10,
                        (newDiameter - currentHeight) / 2
                    )
                }
            }) {

        Text(
            text = "Order 1000 rub",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(bottom = 20.dp),
        )
        Text(
            text = "Swipe to confirm",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(top = 25.dp),
        )

    }

    Box(
        modifier = Modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(2.5f) },
                orientation = Orientation.Horizontal
            )

    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt() + 5, 0) }
                .size(buttonSize)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            val isConfirmed = derivedStateOf { progress.value >= 0.9f }
            Crossfade(targetState = isConfirmed.value) {
                if (it) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_success),
                        contentDescription = "",
                        tint = Color.Green
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow),
                        contentDescription = "",
                        tint = Color.Green
                    )
                }

            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GreenButton() {
    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { 305.dp.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1)
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Color.Green, shape = CircleShape)
            .size(width = 350.dp, height = 50.dp)

            .layout() { measurable, constraints ->
                // Measure the composable
                val placeable = measurable.measure(constraints)

                //get the current max dimension to assign width=height
                val currentHeight = placeable.height
                val currentWidth = placeable.width
                val newDiameter = maxOf(currentHeight, currentWidth)

                //assign the dimension and the center position
                layout(newDiameter, newDiameter) {
                    // Where the composable gets placed
                    placeable.placeRelative(
                        (newDiameter - currentWidth) / 2,
                        (newDiameter - currentHeight) / 2
                    )
                }
            }) {

        Text(
            text = "Order 1000 rub",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(bottom = 20.dp),
        )
        Text(
            text = "Swipe to confirm",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(top = 25.dp),
        )

    }
    OutlinedButton(
        onClick = {
        },
        modifier = Modifier
            .size(45.dp)
            .padding(bottom = 3.dp)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) },
        shape = CircleShape,
        border = BorderStroke(0.dp, Color.White),
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.White,
            contentColor = Color.Green
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = "",
            tint = Color.Green
        )
    }
}

@Composable
fun SwipeButton() {

    OutlinedButton(
        onClick = {
        },
        modifier = Modifier
            .size(45.dp)
            .padding(bottom = 3.dp),
        shape = CircleShape,
        border = BorderStroke(0.dp, Color.White),
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.White,
            contentColor = Color.Green
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = "",
            tint = Color.Green
        )
    }
}


val GreenColor = Color(0xFF2FD286)

enum class ConfirmationState {
    Default, Confirmed
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConfirmationButton(modifier: Modifier = Modifier) {

    val width = 350.dp
    val dragSize = 50.dp

    val swipeableState = rememberSwipeableState(ConfirmationState.Default)
    val sizePx = with(LocalDensity.current) { (width - dragSize).toPx() }
    val anchors = mapOf(0f to ConfirmationState.Default, sizePx to ConfirmationState.Confirmed)
    val progress = derivedStateOf {
        if (swipeableState.offset.value == 0f) 0f else swipeableState.offset.value / sizePx
    }

    Box(
        modifier = modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
            .background(GreenColor, RoundedCornerShape(dragSize))
    ) {
        Column(
            Modifier
                .align(Alignment.Center)
                .alpha(1f - progress.value),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Order 1000 rub", color = Color.White, fontSize = 18.sp)
            Text("Swipe to confirm", color = Color.White, fontSize = 12.sp)
        }

        DraggableControl(
            modifier = Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(dragSize),
            progress = progress.value
        )
    }

}

@Composable
private fun DraggableControl(
    modifier: Modifier,
    progress: Float
) {
    Box(
        modifier
            .padding(4.dp)
            .shadow(elevation = 2.dp, CircleShape, clip = false)
            .background(Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        val isConfirmed = derivedStateOf { progress >= 0.8f }
        Crossfade(targetState = isConfirmed.value) {
            if (it) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = null,
                    tint = GreenColor
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = null,
                    tint = GreenColor
                )
            }

        }
    }
}