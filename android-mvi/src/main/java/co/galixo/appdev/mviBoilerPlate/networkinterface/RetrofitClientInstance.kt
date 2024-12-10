package co.appdev.lms.network.interfaces

import co.galixo.appdev.mviBoilerPlate.util.Constants
import co.galixo.appdev.mviBoilerPlate.util.UnauthorizedInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RetrofitClientInstance {

    companion object {
        private var retrofit: Retrofit? = null
        private val BASE_URL = Constants.Base_URL
        fun getRetrofitInstance(): Retrofit? {
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(UnauthorizedInterceptor())
                .build()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(client)
                    .build()
            }
            return retrofit
        }
    }
}