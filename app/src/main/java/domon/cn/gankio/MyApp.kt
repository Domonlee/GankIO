package domon.cn.gankio

import android.app.Application
import android.content.Context

import domon.cn.gankio.utils.SharedPreferenceUtil

/**
 * Created by Domon on 16-8-19.
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        appContext = applicationContext

        SharedPreferenceUtil.initPreference(appContext)
    }

    companion object {
        var instance: MyApp? = null
            private set

        var appContext: Context? = null
            private set
    }
}
