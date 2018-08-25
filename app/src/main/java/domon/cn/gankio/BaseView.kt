package domon.cn.gankio

/**
 * Created by Domon on 16-10-28.
 */

interface BaseView<T> {
    fun setPresenter(presenter: T)
}
