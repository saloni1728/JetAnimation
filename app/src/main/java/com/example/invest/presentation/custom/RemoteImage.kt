package com.example.invest.presentation.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.invest.R

@Composable
fun RemoteImage(
    url: String,
    modifier: Modifier,
    placeholder: String = "",
    contentDescription: String? = null,
    onError: (() -> Unit)? = null,
    onSuccess: (() -> Unit)? = null,
) {
    val localContext = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(localContext)
            .data(url.ifEmpty { placeholder })
            .crossfade(true)
            .build(),
        error = painterResource(R.drawable.ic_no_image),
        placeholder = painterResource(R.drawable.ic_no_image),
        contentDescription = contentDescription,
        modifier = modifier,
        onError = {onError?.invoke() },
        onSuccess = { onSuccess?.invoke() },
        contentScale = ContentScale.FillWidth
    )
}