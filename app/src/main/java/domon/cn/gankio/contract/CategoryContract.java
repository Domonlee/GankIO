package domon.cn.gankio.contract;

import java.util.List;

import domon.cn.gankio.BasePresenter;
import domon.cn.gankio.BaseView;
import domon.cn.gankio.data.GankInfoData;

/**
 * Created by Domon on 16-10-28.
 */

public interface CategoryContract {
    interface View extends BaseView<Presenter> {
        void reqCategoryData(int type);

        void setCategoryDate(List<GankInfoData> gankInfoDatas);
    }

    interface Presenter extends BasePresenter {
        void reqCategoryData(int type, String index, String count);
    }
}
