package co.galixo.appdev.mviBoilerPlate.data.local

import co.galixo.appdev.mviBoilerPlate.data.model.User


interface DatabaseHelper {

    suspend fun getUsers(): List<User>

}