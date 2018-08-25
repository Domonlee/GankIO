package domon.cn.gankio.presenter

import android.content.Context

import domon.cn.gankio.contract.GirlsContract
import domon.cn.gankio.data.GankGirlsData
import domon.cn.gankio.network.RetrofitHttpUtil
import domon.cn.gankio.utils.progress.ProgressSubscriber
import domon.cn.gankio.utils.progress.SubscriberOnNextListener

/**
 * Created by Domon on 16-8-12.
 */
class GirlsPresenter(private val mIGirlsView: GirlsContract.View, private val mContext: Context) : GirlsContract.Presenter {

    init {
        mIGirlsView.setPresenter(this)
    }

    override fun reqGrilsGankData(index: String, count: String) {
        val progressSubscriber = ProgressSubscriber<GankGirlsData>(SubscriberOnNextListener<GankGirlsData> { gankGirlsData -> mIGirlsView.setData(gankGirlsData) }, mContext)

        RetrofitHttpUtil.getInstance().getRxGankGrilsData(progressSubscriber, count, index)
    }

    override fun start() {

    }
}
