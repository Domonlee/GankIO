package domon.cn.gankio.contract;

import java.util.List;

import domon.cn.gankio.BasePresenter;
import domon.cn.gankio.BaseView;

/**
 * Created by Domon on 16-10-28.
 */

public interface JianDanContract {

    interface View extends BaseView<Presenter> {
        void setProgressBarVisibility(int visibility);

        void setData(List<String> jiandanGirlsData);
    }

    interface Presenter extends BasePresenter {
        void reqJiandanGirls(String index, String count);
    }
}
