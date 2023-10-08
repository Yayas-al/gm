package com.dev.gm.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.toOffset
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import coil.transform.Transformation
import com.dev.gm.R

@Composable
fun NetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String = "",
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    zoomable: Boolean = false,
    colorFilter: ColorFilter? = null,
    placeholderDrawableRes: Int? = null,
    crossFade: Int? = null,
    transformations: List<Transformation>? = null,
    onLoading: @Composable () -> Unit,
    onError: @Composable () -> Unit,
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(IntOffset(0, 0)) }
    Box(
        modifier = if(zoomable) modifier.pointerInput(Unit){
            detectTransformGestures { _, pan, zoom, _ ->
                scale *= zoom
                offset = IntOffset(
                    (offset.x + pan.x).toInt(),
                    (offset.y + pan.y).toInt()
                )
            }
        } else modifier
    ){
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = url).apply(block = fun ImageRequest.Builder.() {
                size(Size.ORIGINAL)
                scale(scale = Scale.FIT)
                placeholderDrawableRes?.let {
                    placeholder(drawableResId = it)
                }
                error(R.drawable.ic_warning)
                crossFade?.let {
                    crossfade(durationMillis = it)
                }
                transformations?.let {
                    transformations(transformations = it)
                }
            }).build()
        )
        val imageState = painter.state

        if(imageState is AsyncImagePainter.State.Loading){
            onLoading()
        }
        if(imageState is AsyncImagePainter.State.Error){
            onError()
        }
        if(zoomable){
            Image(
                modifier = Modifier.fillMaxSize().graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x.toFloat(),
                    translationY = offset.y.toFloat()
                ),
                painter = painter,
                contentDescription = contentDescription,
                contentScale = contentScale,
                alignment = alignment,
                alpha = alpha,
                colorFilter = colorFilter
            )
        }else{
            Image(
                painter = painter,
                modifier = Modifier.fillMaxSize(),
                contentDescription = contentDescription,
                contentScale = contentScale,
                alignment = alignment,
                alpha = alpha,
                colorFilter = colorFilter
            )
        }

    }
}