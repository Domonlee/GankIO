package domon.cn.gankio.presenter

import android.content.Context

import domon.cn.gankio.contract.CategoryContract
import domon.cn.gankio.data.GankCategoryData
import domon.cn.gankio.network.RetrofitHttpUtil
import domon.cn.gankio.network.rxAPIs
import domon.cn.gankio.utils.progress.ProgressSubscriber
import domon.cn.gankio.utils.progress.SubscriberOnNextListener

/**
 * Created by Domon on 16-8-22.
 */
class CategoryPresenter(private val mCategoryView: CategoryContract.View, private val mContext: Context) : CategoryContract.Presenter {

    init {
        mCategoryView.setPresenter(this)
    }

    override fun reqCategoryData(type: Int, index: String, count: String) {
        val progressSubscriber = ProgressSubscriber<GankCategoryData>(
                SubscriberOnNextListener<GankCategoryData> { gankCategoryData -> mCategoryView.setCategoryDate(gankCategoryData.results) }, mContext)

        RetrofitHttpUtil.getInstance().getRxGankCategoryData(progressSubscriber, rxAPIs.GankCategory[type], count, index)
    }

    override fun start() {

    }
}
