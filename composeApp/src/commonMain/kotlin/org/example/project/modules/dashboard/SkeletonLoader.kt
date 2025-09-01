package org.example.project.modules.dashboard

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun SkeletonLoader(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        repeat(10) {
            SkeletonItem()
        }
    }
}

@Composable
fun SkeletonItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(
            modifier = Modifier
                .size(48.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth(0.7f)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(0.9f)
                    .shimmerEffect()
            )
        }
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200),
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f),
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
            ),
            start = Offset.Zero,
            end = Offset(x = translateAnim.value, y = translateAnim.value)
        )
    )
}
