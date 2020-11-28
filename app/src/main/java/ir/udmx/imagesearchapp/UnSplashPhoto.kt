package ir.udmx.imagesearchapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UnSplashPhoto(
    val id: String,
    val description: String?,
    val urls: UnSplashPhotoUrls,
    val user: UnSplashPhotoUsers
) : Parcelable {
    @Parcelize
    data class UnSplashPhotoUrls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String,
    ) : Parcelable

    @Parcelize
    data class UnSplashPhotoUsers(
        val name: String,
        val username: String,
    ) : Parcelable {
        val attributionUrl
            get() = "https://unsplash.com/$username?utm_source=ImageSearchApp&utm_medium=referral"
    }
}