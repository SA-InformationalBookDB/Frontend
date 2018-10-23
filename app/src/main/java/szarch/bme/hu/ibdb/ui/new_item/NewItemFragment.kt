package szarch.bme.hu.ibdb.ui.new_item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.ui.base.InjectedFragment

class NewItemFragment : InjectedFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_item, null)
    }

}