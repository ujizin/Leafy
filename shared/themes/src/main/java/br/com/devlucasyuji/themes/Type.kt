package br.com.devlucasyuji.themes

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.devlucasyuji.camerareminder.shared.themes.R

private val PoppinsFamily = FontFamily(
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_regular, FontWeight.Normal)
)

internal val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
    ),
    bodyMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 12.sp,
    ),
)
