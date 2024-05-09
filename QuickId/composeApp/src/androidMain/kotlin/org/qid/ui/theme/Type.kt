package org.qid.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.qid.R

val manrope = FontFamily(
    listOf(
        Font(R.font.manrope_bold, FontWeight.Bold),
        Font(R.font.manrope_extrabold, FontWeight.ExtraBold),
        Font(R.font.manrope_extralight, FontWeight.ExtraLight),
        Font(R.font.manrope_light, FontWeight.Light),
        Font(R.font.manrope_medium, FontWeight.Medium),
        Font(R.font.manrope_regular, FontWeight.Normal),
        Font(R.font.manrope_semibold, FontWeight.SemiBold),
    )
)


// Set of Material typography styles to start with
//val displayLarge: TextStyle = TypographyTokens.DisplayLarge,
//val displayMedium: TextStyle = TypographyTokens.DisplayMedium,
//val displaySmall: TextStyle = TypographyTokens.DisplaySmall,
//val headlineLarge: TextStyle = TypographyTokens.HeadlineLarge,
//val headlineMedium: TextStyle = TypographyTokens.HeadlineMedium,
//val headlineSmall: TextStyle = TypographyTokens.HeadlineSmall,
//val titleLarge: TextStyle = TypographyTokens.TitleLarge,
//val titleMedium: TextStyle = TypographyTokens.TitleMedium,
//val titleSmall: TextStyle = TypographyTokens.TitleSmall,
//val bodyLarge: TextStyle = TypographyTokens.BodyLarge,
//val bodyMedium: TextStyle = TypographyTokens.BodyMedium,
//val bodySmall: TextStyle = TypographyTokens.BodySmall,
//val labelLarge: TextStyle = TypographyTokens.LabelLarge,
//val labelMedium: TextStyle = TypographyTokens.LabelMedium,
//val labelSmall: TextStyle = TypographyTokens.LabelSmall,
val Typography = Typography(
    displayLarge = TextStyle(fontFamily = manrope, fontSize = 57.sp),
    displayMedium = TextStyle(fontFamily = manrope, fontSize = 45.sp),
    displaySmall = TextStyle(fontFamily = manrope, fontSize = 36.sp),

    headlineLarge = TextStyle(fontFamily = manrope, fontSize = 32.sp),
    headlineMedium = TextStyle(fontFamily = manrope, fontSize = 28.sp),
    headlineSmall = TextStyle(fontFamily = manrope, fontSize = 24.sp),

    titleLarge = TextStyle(fontFamily = manrope, fontSize = 22.sp, fontWeight = FontWeight.Bold),
    titleMedium = TextStyle(fontFamily = manrope, fontSize = 18.sp, fontWeight = FontWeight.Bold),
    titleSmall = TextStyle(fontFamily = manrope, fontSize = 16.sp, fontWeight = FontWeight.Bold),

    bodyLarge = TextStyle(fontFamily = manrope, fontSize = 16.sp),
    bodyMedium = TextStyle(fontFamily = manrope, fontSize = 14.sp),
    bodySmall = TextStyle(fontFamily = manrope, fontSize = 12.sp),

    labelLarge = TextStyle(fontFamily = manrope, fontSize = 14.sp),
    labelMedium = TextStyle(fontFamily = manrope, fontSize = 12.sp),
    labelSmall = TextStyle(fontFamily = manrope, fontSize = 11.sp),
)