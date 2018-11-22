package szarch.bme.hu.ibdb.ui.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import szarch.bme.hu.ibdb.util.Contexts
import kotlin.coroutines.CoroutineContext

abstract class Presenter<S> : CoroutineScope {

    protected var screen: S? = null

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext = Contexts.UI + job

    open fun attachScreen(screen: S) {
        this.screen = screen
    }

    open fun detachScreen() {
        this.screen = null
        job.cancel()
    }

}