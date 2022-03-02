package com.shinaz.newapp.ui.base

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<T : ViewDataBinding?, V : ViewModel?> : Fragment() {
    var viewDataBinding: T? = null
        private set

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding!!.root
    }


    override fun onDetach() {
        clearMemory()
        super.onDetach()
    }


//    val baseActivity: com.shinaz.newapp.ui.base.BaseActivity<*, *>?
//        get() = mActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
//        if (context is com.shinaz.newapp.ui.base.BaseActivity) {
//            mActivity = context as com.shinaz.newapp.ui.base.BaseActivity<*, *>
//        }
    }

    fun onBackPress(): Boolean {
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        clearMemory()
    }

    private fun clearMemory() {
//        mActivity = null
        viewDataBinding = null
    }

}