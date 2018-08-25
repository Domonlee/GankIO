package domon.cn.gankio.contract

import domon.cn.gankio.BasePresenter
import domon.cn.gankio.BaseView
import domon.cn.gankio.data.GankInfoData

/**
 * Created by Domon on 16-10-28.
 */

interface CategoryContract {
    interface View : BaseView<Presenter> {
        fun reqCategoryData(type: Int)

        fun setCategoryDate(gankInfoDatas: List<GankInfoData>)
    }

    interface Presenter : BasePresenter {
        fun reqCategoryData(type: Int, index: String, count: String)
    }
}
