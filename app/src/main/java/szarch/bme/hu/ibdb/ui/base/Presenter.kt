package szarch.bme.hu.ibdb.ui.base

import kotlinx.coroutines.Job

abstract class Presenter<S> {

    protected var screen: S? = null

    protected var job: Job = Job()

    open fun attachScreen(screen: S) {
        this.screen = screen
    }

    open fun detachScreen() {
        this.screen = null
        job.cancel()
    }

}