package com.dmity.courutinesotus.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dmity.courutinesotus.network.Api
import com.dmity.courutinesotus.network.MoviesService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

abstract class BaseActivity : AppCompatActivity() {

    protected val scope = CoroutineScope(Dispatchers.Default)

    protected inline fun <reified T : Any> List<T?>?.toNotNullList() = this?.filterNotNull().orEmpty()

    protected fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    protected val service: MoviesService by lazy { Api.getInstance().create(MoviesService::class.java) }

    override fun onStop() {
        scope.cancel("onStop")
        super.onStop()
    }
}