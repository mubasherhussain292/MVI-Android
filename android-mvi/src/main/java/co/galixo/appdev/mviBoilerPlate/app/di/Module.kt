package co.galixo.appdev.mviBoilerPlate.app.di

import co.galixo.appdev.mviBoilerPlate.networkinterface.ApiInterface
import okhttp3.OkHttpClient
import co.galixo.appdev.mviBoilerPlate.util.UnauthorizedInterceptor
import co.galixo.appdev.mviBoilerPlate.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val netModule = module {
    fun provideOkHttpClient(): OkHttpClient {
        val okhttpClientBuilder = OkHttpClient.Builder()
        okhttpClientBuilder.addInterceptor(UnauthorizedInterceptor())
        return okhttpClientBuilder.build()
    }
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
}

val viewModelScope = module {
    viewModel { MainViewModel(get()) }
}

val apiModule = module {
    fun provideUserApi(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    single { provideUserApi(get()) }
}