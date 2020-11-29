package ir.udmx.imagesearchapp.data

import androidx.paging.PagingSource
import ir.udmx.imagesearchapp.api.UnSplashApi
import retrofit2.HttpException
import java.io.IOException


private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class UnSplashPagingSource(
    private val unSplashApi: UnSplashApi,
    private val query: String
) : PagingSource<Int, UnSplashPhoto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnSplashPhoto> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = unSplashApi.searchPhotos(query, position, params.loadSize)
            val photos = response.results
            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

}