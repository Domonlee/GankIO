package domon.cn.gankio.contract;

import domon.cn.gankio.BasePresenter;
import domon.cn.gankio.BaseView;
import domon.cn.gankio.data.GankContentData;

/**
 * Created by Domon on 16-10-28.
 */

public interface HomeContract {

    interface View extends BaseView<Presenter> {
        void setData(GankContentData data);
    }

    interface Presenter extends BasePresenter {
        void reqHomeGankData();
    }
}
