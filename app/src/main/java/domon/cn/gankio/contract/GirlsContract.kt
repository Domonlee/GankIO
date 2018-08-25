package domon.cn.gankio.contract

import domon.cn.gankio.BasePresenter
import domon.cn.gankio.BaseView
import domon.cn.gankio.data.GankGirlsData

/**
 * Created by Domon on 16-10-28.
 */

interface GirlsContract {

    interface View : BaseView<Presenter> {
        fun getGankGirlsData()

        fun setData(gankGirlsData: GankGirlsData)
    }

    interface Presenter : BasePresenter {
        fun reqGrilsGankData(index: String, count: String)
    }
}
