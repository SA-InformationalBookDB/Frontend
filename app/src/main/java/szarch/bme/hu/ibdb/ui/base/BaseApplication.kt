package szarch.bme.hu.ibdb.ui.base

import android.app.Application
import android.content.Context
import android.support.annotation.CallSuper
import android.support.multidex.MultiDex

abstract class BaseApplication : Application() {

    abstract val injector: BaseComponent

    @CallSuper
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        setupInjector()
    }

    protected abstract fun setupInjector()

}