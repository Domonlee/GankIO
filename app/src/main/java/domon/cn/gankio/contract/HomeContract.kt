package domon.cn.gankio.contract

import domon.cn.gankio.BasePresenter
import domon.cn.gankio.BaseView
import domon.cn.gankio.data.GankContentData

/**
 * Created by Domon on 16-10-28.
 */

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun setData(data: GankContentData)
    }

    interface Presenter : BasePresenter {
        fun reqHomeGankData()
    }
}
