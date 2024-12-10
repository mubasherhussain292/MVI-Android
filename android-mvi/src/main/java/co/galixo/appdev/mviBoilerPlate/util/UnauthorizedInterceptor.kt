package co.galixo.appdev.mviBoilerPlate.util

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class UnauthorizedInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        if (response.code() == 401) {
            Log.i("UnauthorizedInterceptor", response.code().toString())
        }
        return response
    }

}
