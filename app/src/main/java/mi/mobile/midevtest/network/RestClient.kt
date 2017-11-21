package mi.mobile.midevtest.network

import io.reactivex.Observable
import mi.mobile.midevtest.BuildConfig
import mi.mobile.midevtest.constant.ApiEndpoint
import mi.mobile.midevtest.model.Delivery
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

/**
 * Created by ikhsan on 21/11/17.
 */

interface RestClient
{
    @GET(ApiEndpoint.DELIVERIES)
    fun fetchDeliveries(): Observable<List<Delivery>>

    companion object {

        const val BASE_URL = "http://mi-mobile-dev.ap-southeast-1.elasticbeanstalk.com"

        /**
         * Factory method
         */
        fun create(): RestClient {

            val client = OkHttpClient().newBuilder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                    })
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(client)
                    .build()
            return retrofit.create(RestClient::class.java)
        }
    }

}