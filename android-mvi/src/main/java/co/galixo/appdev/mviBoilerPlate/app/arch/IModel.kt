package co.galixo.appdev.mviBoilerPlate.app.arch

import androidx.lifecycle.LiveData
import kotlinx.coroutines.channels.Channel

interface IModel<S: IState, I: IIntent> {
    val intents: Channel<I>
    val state: LiveData<S>
}