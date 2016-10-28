package domon.cn.gankio.contract;

import domon.cn.gankio.BasePresenter;
import domon.cn.gankio.BaseView;
import domon.cn.gankio.data.GankGirlsData;

/**
 * Created by Domon on 16-10-28.
 */

public interface GirlsContract {

    interface View extends BaseView<Presenter> {
        void getGankGirlsData();

        void setData(GankGirlsData gankGirlsData);
    }

    interface Presenter extends BasePresenter {
        void reqGrilsGankData(String index, String count);
    }
}
