package szarch.bme.hu.ibdb

import com.facebook.stetho.Stetho
import szarch.bme.hu.ibdb.di.AppComponent
import szarch.bme.hu.ibdb.di.ApplicationModule
import szarch.bme.hu.ibdb.di.DaggerAppComponent
import szarch.bme.hu.ibdb.ui.base.BaseApplication

open class IBDBApplication : BaseApplication() {

    override lateinit var injector: AppComponent


    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
    override fun setupInjector() {
        injector = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}