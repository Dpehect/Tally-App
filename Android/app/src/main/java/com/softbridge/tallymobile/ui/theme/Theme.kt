package com.softbridge.tallymobile.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val TallyBlue = Color(0xFF0070D7)
val TallyPink = Color(0xFFF81CE5)
val Ink = Color(0xFF37352F)
val Canvas = Color(0xFFFFFDFC)

private val colors = lightColorScheme(primary = TallyBlue, secondary = TallyPink, background = Canvas, surface = Color.White, onBackground = Ink, onSurface = Ink)

@Composable fun TallyTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = colors, typography = androidx.compose.material3.Typography(), content = content)
}
