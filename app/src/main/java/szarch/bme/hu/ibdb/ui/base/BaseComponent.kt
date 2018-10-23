package szarch.bme.hu.ibdb.ui.base

interface BaseComponent {

    fun inject(baseActivity: InjectedActivity)

    fun inject(baseFragment: InjectedFragment)

}