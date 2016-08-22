package domon.cn.gankio.view;

import java.util.List;

import domon.cn.gankio.data.GankInfoData;

/**
 * Created by Domon on 16-8-19.
 */
public interface ICategoryView {

    void reqCategoryData(int type);

    void setProgressDialogVisibility(int visibility);

    void setCategoryDate(List<GankInfoData> gankInfoDatas);
}
