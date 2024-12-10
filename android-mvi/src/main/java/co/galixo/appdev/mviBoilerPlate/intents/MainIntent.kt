package co.galixo.appdev.mviBoilerPlate.intents

import co.galixo.appdev.mviBoilerPlate.app.arch.IIntent

sealed class MainIntent : IIntent {
    object RefreshUsers : MainIntent()
    object FetchUsers : MainIntent()
    object RemoveUsers : MainIntent()
    object SearchUsers : MainIntent()
}

