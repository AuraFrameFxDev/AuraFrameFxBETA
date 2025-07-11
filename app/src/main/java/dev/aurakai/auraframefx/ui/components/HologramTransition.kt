package dev.aurakai.auraframefx.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun HologramTransition(
    visible: Boolean,
    modifier: Modifier = Modifier,
    glowColor: Color = Color(0xFF00FFCC), // Neon teal
    glitchColor: Color = Color(0xFFB388FF), // Neon purple
    backgroundColor: Color = Color.Black,
    content: @Composable () -> Unit,
) {
    // Animate progress: 0f (start/dispersed) to 1f (fully materialized)
    val progress by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 900, easing = FastOutSlowInEasing)
    )

    Box(modifier = modifier.fillMaxSize()) {
        // Hologram effect layer
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val blockSize = 24f
            val scanlineSpacing = 8f
            val dispersion = (1f - progress) * height

            // Draw pitch black background
            drawRect(backgroundColor)

            // Scanline shimmer
            for (y in 0..(height / scanlineSpacing).roundToInt()) {
                val alpha = 0.10f + 0.15f * (1f - progress)
                drawRect(
                    color = glowColor.copy(alpha = alpha),
                    topLeft = Offset(0f, y * scanlineSpacing),
                    size = androidx.compose.ui.geometry.Size(width, 2f * progress)
                )
            }

            // Blocky pixel materialization/dispersal
            for (x in 0..(width / blockSize).roundToInt()) {
                for (y in 0..(height / blockSize).roundToInt()) {
                    val blockY = y * blockSize
                    // Blocks rise/fall into place during transition
                    val blockProgress = progress + 0.2f * sin(x + y * 0.5f)
                    val reveal = (blockY < height * blockProgress)
                    if (reveal) {
                        drawRect(
                            color = glowColor.copy(alpha = 0.13f + 0.10f * sin(x * 0.7f + y)),
                            topLeft = Offset(x * blockSize, blockY),
                            size = androidx.compose.ui.geometry.Size(blockSize, blockSize)
                        )
                    } else {
                        // Glitchy dispersal
                        if (progress < 1f && Random.nextFloat() > progress) {
                            drawRect(
                                color = glitchColor.copy(alpha = 0.11f),
                                topLeft = Offset(
                                    x * blockSize,
                                    blockY + dispersion * Random.nextFloat()
                                ),
                                size = androidx.compose.ui.geometry.Size(blockSize, blockSize)
                            )
                        }
                    }
                }
            }
        }
        // Content fades in as hologram forms
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = progress } // Corrected usage of graphicsLayer
        ) {
            content() // Content is drawn inside the Box with the modifier
        }
    }
}
