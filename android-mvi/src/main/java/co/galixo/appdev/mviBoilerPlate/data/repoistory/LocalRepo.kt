package co.galixo.appdev.mviBoilerPlate.data.repoistory

import androidx.room.Database
import androidx.room.RoomDatabase
import co.galixo.appdev.mviBoilerPlate.data.local.DAOInterface
import co.galixo.appdev.mviBoilerPlate.data.model.User

@Database(entities = [User::class], version = 1)
abstract class LocalRepo : RoomDatabase() {

    abstract fun userDao(): DAOInterface

}