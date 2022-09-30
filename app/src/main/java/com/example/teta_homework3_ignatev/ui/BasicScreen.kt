package com.example.teta_homework3_ignatev.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
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


@Composable
fun BasicScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SwipeableBasic()
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableBasic() {
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