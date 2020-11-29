package ir.udmx.imagesearchapp.api

import ir.udmx.imagesearchapp.data.UnSplashPhoto

data class UnSplashResponse(
    val results : List<UnSplashPhoto>
)