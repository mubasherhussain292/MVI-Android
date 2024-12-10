package co.galixo.appdev.mviBoilerPlate.data.repoistory

import android.content.Context
import androidx.room.Room
import co.galixo.appdev.mviBoilerPlate.view.MainActivity

object DatabaseBuilder {
    private var INSTANCE: LocalRepo? = null

    fun getInstance(context: Context): LocalRepo {
        if (INSTANCE == null) {
            synchronized(LocalRepo::class) {
                INSTANCE = buildRoomDB(MainActivity.context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            LocalRepo::class.java,
            context.applicationContext.packageName
        ).build()

}