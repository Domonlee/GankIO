package domon.cn.gankio.view;

import java.util.List;

import domon.cn.gankio.data.GankContentData;

/**
 * Created by Domon on 16-8-11.
 */
public interface IHomeView {
    void getToadyGank();

    void getGankDateInfo();

    void setData(GankContentData data);

    void setDateInfo(List<String> dates);

    void setProgressDialogVisibility(int visibility);
}
