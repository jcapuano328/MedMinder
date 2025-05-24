package com.ica.medminder.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier


@Composable
fun PngIcon(resId: Int, desc: String, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = desc,
        modifier = modifier
    )
}