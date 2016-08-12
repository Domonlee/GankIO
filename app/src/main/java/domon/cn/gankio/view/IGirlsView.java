package domon.cn.gankio.view;

import domon.cn.gankio.data.GankGirlsData;

/**
 * Created by Domon on 16-8-12.
 */
public interface IGirlsView {
    void setProgressBarVisibility(int visibility);

    void getGankGirlsData();

    void setData(GankGirlsData gankGirlsData);
}
