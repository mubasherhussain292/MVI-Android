package co.galixo.appdev.mviBoilerPlate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import co.galixo.appdev.mviBoilerPlate.states.MainState
import co.galixo.appdev.mviBoilerPlate.intents.MainIntent
import co.galixo.appdev.mviBoilerPlate.app.arch.IModel
import co.galixo.appdev.mviBoilerPlate.data.repoistory.Repoistory
import co.galixo.appdev.mviBoilerPlate.networkinterface.ApiInterface


class MainViewModel(private val api:ApiInterface) : ViewModel(), IModel<MainState, MainIntent> {

    val query = MutableLiveData<String>().apply { value = "" }
    private val repoistory = Repoistory.getInstance()
    override val intents: Channel<MainIntent> = Channel(Channel.UNLIMITED)
    private val _state = MutableLiveData<MainState>().apply { value = MainState() }
    override val state: LiveData<MainState>
        get() = _state
    init {
        handlerIntent()
    }

    private fun handlerIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { userIntent ->
                when(userIntent) {
                    MainIntent.RefreshUsers -> fetchData()
                    MainIntent.FetchUsers -> fetchData()
                    MainIntent.RemoveUsers -> removeData()
                    MainIntent.SearchUsers -> searchData(query.value!!)
                }
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateState { it.copy(isLoading = true) }
                updateState { it.copy(isLoading = false, users = repoistory.GetUsers()) }
            } catch (e: Exception) {
                updateState { it.copy(isLoading = false, errorMessage = e.message) }
            }
        }
    }
    private fun removeData(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateState { it.copy(isLoading = true) }
                updateState {
                    it.copy(isLoading = false, users = null)
                }
            } catch (e: Exception) {
                updateState { it.copy(isLoading = false, errorMessage = e.message) }
            }
        }
    }
    private fun searchData(query:String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateState { it.copy(isLoading = true) }
                val newData = repoistory.GetUsers()
                val filteredData = newData?.filter { it -> it.login.contains(query,true) }
                updateState { it.copy(isLoading = false, users = filteredData) }
            } catch (e: Exception) {
                updateState { it.copy(isLoading = false, errorMessage = e.message) }
            }
        }
    }
    private suspend fun updateState(handler: suspend (intent: MainState) -> MainState) {
        _state.postValue(handler(state.value!!))
    }
}