package domon.cn.gankio.view;

import java.util.List;

/**
 * Created by Domon on 16-8-30.
 */
public interface IJiandanView {
    void setProgressBarVisibility(int visibility);

    void getJiandanGirlsData();

    void setData(List<String> jiandanGirlsData);
}
