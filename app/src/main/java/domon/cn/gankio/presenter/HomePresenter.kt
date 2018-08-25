package domon.cn.gankio.presenter

import android.content.Context

import domon.cn.gankio.contract.HomeContract
import domon.cn.gankio.data.GankContentData
import domon.cn.gankio.network.RetrofitHttpUtil
import domon.cn.gankio.utils.SharedPreferenceUtil
import domon.cn.gankio.utils.progress.ProgressSubscriber
import domon.cn.gankio.utils.progress.SubscriberOnNextListener

/**
 * Created by Domon on 16-8-11.
 */
class HomePresenter(private val iHomeView: HomeContract.View, private val mContext: Context) : HomeContract.Presenter {

    private val date: String?
        get() = if (SharedPreferenceUtil.getStrListValue("gankDateInfoList") != null) {
            SharedPreferenceUtil.getStrListValue("gankDateInfoList")!![0].replace("-", "/")
        } else null

    init {

        iHomeView.setPresenter(this)
    }

    override fun reqHomeGankData() {
        val progressSubscriber = ProgressSubscriber<GankContentData>(SubscriberOnNextListener<GankContentData> { gankContentData -> iHomeView.setData(gankContentData) }, mContext)
        RetrofitHttpUtil.getInstance().getRxGankInfoData(progressSubscriber, date)
    }

    override fun start() {
        reqHomeGankData()
    }
}