package szarch.bme.hu.ibdb.di

import dagger.Component
import szarch.bme.hu.ibdb.ui.base.BaseComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        InteractorModule::class,
        NetworkModule::class,
        ProviderModule::class,
        UIModule::class
    ]
)
interface AppComponent : BaseComponent