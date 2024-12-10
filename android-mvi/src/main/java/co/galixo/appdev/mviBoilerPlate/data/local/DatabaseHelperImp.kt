package co.galixo.appdev.mviBoilerPlate.data.local

import co.galixo.appdev.mviBoilerPlate.data.model.User
import co.galixo.appdev.mviBoilerPlate.data.repoistory.LocalRepo


class DatabaseHelperImpl(private val appDatabase: LocalRepo) : DatabaseHelper {

    override suspend fun getUsers(): List<User> = appDatabase.userDao().getAll()


}