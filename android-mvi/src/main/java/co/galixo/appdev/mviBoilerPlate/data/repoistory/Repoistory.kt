package co.galixo.appdev.mviBoilerPlate.data.repoistory

import co.appdev.lms.network.interfaces.RetrofitClientInstance
import co.galixo.appdev.mviBoilerPlate.data.model.User
import co.galixo.appdev.mviBoilerPlate.networkinterface.ApiInterface

class Repoistory {

    val retrofit = RetrofitClientInstance.getRetrofitInstance()
    private var networkApiInterface = retrofit?.create(ApiInterface::class.java)

    companion object {
        private var instance: Repoistory? = null
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Repoistory().also { instance = it }
            }
    }

    suspend fun GetUsers(): List<User>? {
        var data: List<User>? = null
        // Just a concept to user LocalDB
//        val localDb = DatabaseBuilder.getInstance(context)
//        val localData = localDb.userDao().getAll()
//        var data : List<User>? = null
//        if(localData.isEmpty()){
//            data= networkApiInterface?.getUser()
//            if (data != null) {
//                localDb.userDao().insertAll(data)
//            }
//        }
//        else{
//            networkApiInterface?.getUser()?.let { localDb.userDao().insertAll(it) }
//            data = localDb.userDao().getAll()
//        }

        data = networkApiInterface?.getUser()
        return data
    }
}