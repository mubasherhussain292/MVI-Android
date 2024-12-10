package co.galixo.appdev.mviBoilerPlate.networkinterface

import co.galixo.appdev.mviBoilerPlate.data.model.User
import retrofit2.http.GET


interface ApiInterface {
    @GET("users")
    suspend fun getUser(): List<User>
}