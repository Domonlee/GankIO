package domon.cn.gankio.ui.activity

import android.content.Intent
import android.os.Bundle

import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntroFragment
import com.socks.library.KLog

import domon.cn.gankio.R
import domon.cn.gankio.data.GankHistoryData
import domon.cn.gankio.network.RetrofitHttpUtil
import domon.cn.gankio.ui.fragment.SplashFragment
import domon.cn.gankio.utils.SharedPreferenceUtil
import rx.Subscriber

/**
 * Created by Domon on 16-9-21.
 */

class IntroActivity : AppIntro() {

    override fun init(savedInstanceState: Bundle?) {
        reqGankHistoryData()

        if (SharedPreferenceUtil.getIntegerValue("isFirstStart") == -1) {
            val color = resources.getColor(R.color.colorPrimary)
            addSlide(AppIntroFragment.newInstance("最新", "能及时查看干货的最新信息", R.drawable.intro1, color))
            addSlide(AppIntroFragment.newInstance("最全", "所有干货分类信息一网打尽", R.drawable.intro2, color))
            addSlide(AppIntroFragment.newInstance("最美", "读干货学知识看福利长见识", R.drawable.intro3, color))

            setBarColor(color)

            setZoomAnimation()

            setDoneText("立即开启")
            setSkipText("跳过")
        } else {
            //fixme how to close indicator
            addSlide(SplashFragment())
            showSkipButton(false)
            isProgressButtonEnabled = false
        }
    }

    override fun onDonePressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onSkipPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun reqGankHistoryData() {
        val subscriber = object : Subscriber<GankHistoryData>() {
            override fun onCompleted() {}

            override fun onError(e: Throwable) {
                KLog.e(e)
            }

            override fun onNext(gankHistoryData: GankHistoryData) {
                SharedPreferenceUtil.setStrListValue("gankDateInfoList", gankHistoryData.results)
                KLog.e()
            }
        }
        RetrofitHttpUtil.getInstance().getRxGankHistoryDare(subscriber)
    }
}
