package com.nelsito.fifteenpuzzle.infrastructure

import android.graphics.Bitmap
import com.nelsito.fifteenpuzzle.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import android.graphics.BitmapFactory
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.io.InputStream


class PhotoRepository {

    private var okhttpClient: OkHttpClient
    private val service : UnsplashClient

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okhttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(UnsplashClient::class.java)
    }

    suspend fun getRandom() : Photo {
        return service.getRandom(BuildConfig.UNSPLAH_API_KEY)
    }

    fun getBitmap(url: String): Bitmap {
        val request: Request = Request.Builder()
            .url(url)
            .build()

        val response: Response = okhttpClient.newCall(request).execute()
        if (!response.isSuccessful) throw IOException("Unexpected code $response")

        val inputStream: InputStream? = response.body?.byteStream()
        return crop(BitmapFactory.decodeStream(inputStream))
    }


    private fun crop(bitmap: Bitmap): Bitmap {
        if (bitmap.width >= bitmap.height){
            return Bitmap.createBitmap(
                bitmap,
                bitmap.width /2 - bitmap.height /2,
                0,
                bitmap.height,
                bitmap.height
            )
        }else{
            return Bitmap.createBitmap(
                bitmap,
                0,
                bitmap.height /2 - bitmap.width /2,
                bitmap.width,
                bitmap.width
            )
        }
    }
}

interface UnsplashClient {
    @GET("photos/random")
    suspend fun getRandom(@Query("client_id") accessKey: String): Photo
}

data class Photo(val width: Int, val height: Int, val color: String, val urls: PhotoUrls)

data class PhotoUrls(val regular: String)