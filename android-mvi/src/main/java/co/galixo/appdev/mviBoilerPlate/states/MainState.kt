package co.galixo.appdev.mviBoilerPlate.states

import co.galixo.appdev.mviBoilerPlate.app.arch.IState
import co.galixo.appdev.mviBoilerPlate.data.model.User

data class MainState(
    val users: List<User>? = listOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
) : IState