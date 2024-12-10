package co.galixo.appdev.mviBoilerPlate.app.arch

interface IView<S: IState> {
    fun render(state: S)
}