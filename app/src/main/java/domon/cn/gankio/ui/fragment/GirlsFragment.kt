package domon.cn.gankio.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import domon.cn.gankio.R
import domon.cn.gankio.contract.GirlsContract
import domon.cn.gankio.data.GankGirlsData
import domon.cn.gankio.presenter.GirlsPresenter
import domon.cn.gankio.ui.adapter.GankGirlsDataAdapter
import kotlinx.android.synthetic.main.fragment_girls.view.*

/**
 * Created by Domon on 16-8-12.
 */
class GirlsFragment : Fragment(), GirlsContract.View {

    lateinit var girlsRecyclerView : XRecyclerView
    private var mGankGirlsAdapter: GankGirlsDataAdapter? = null
    private var mGirlsPresenter: GirlsContract.Presenter? = null
    private var mCurrentIndex = 1
    private var mLastIndex = 1
    private val mCount = 10

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_girls, container, false)

        getGankGirlsData()

        girlsRecyclerView = view.girls_rv

        mGankGirlsAdapter = GankGirlsDataAdapter(context)
        girlsRecyclerView.layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)
        girlsRecyclerView.adapter = mGankGirlsAdapter

        loadMore()

        return view
    }

    override fun getGankGirlsData() {
        mGirlsPresenter = GirlsPresenter(this, context)
        mGirlsPresenter!!.reqGrilsGankData(mCurrentIndex.toString() + "", mCount.toString() + "")
    }

    override fun setData(gankGirlsData: GankGirlsData) {
        if (mCurrentIndex > mLastIndex) {
            mGankGirlsAdapter!!.addAllWithNotifyItem(gankGirlsData.results, mCount * mCurrentIndex)
            mLastIndex = mCurrentIndex
        } else {
            mGankGirlsAdapter!!.addAll(gankGirlsData.results)
            mGankGirlsAdapter!!.notifyDataSetChanged()
        }
    }

    private fun loadMore() {
        girlsRecyclerView.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onRefresh() {
                mCurrentIndex = 1
                mGirlsPresenter!!.reqGrilsGankData(mCurrentIndex.toString() + "", "10")
                girlsRecyclerView.refreshComplete()
                Toast.makeText(context, "refresh successful", Toast.LENGTH_SHORT).show()
            }

            override fun onLoadMore() {
                ++mCurrentIndex
                getGankGirlsData()
                girlsRecyclerView.loadMoreComplete()
            }
        })
        girlsRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin)
    }

    override fun setPresenter(presenter: GirlsContract.Presenter?) {
        if (presenter != null) {
            mGirlsPresenter = presenter
        }
    }
}
