package szarch.bme.hu.ibdb.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment

abstract class InjectedFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as? BaseApplication)
            ?.injector
            ?.inject(this)
            ?: throw IllegalStateException("InjectedFragment should not be used without an Application that inherits from BaseApplication")
    }

}