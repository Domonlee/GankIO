package domon.cn.gankio.presenter;

/**
 * Created by Domon on 16-8-12.
 */
public interface IGirlsPresenter {
    void reqGrilsGankData(int index);

    void setProgressBarVisibility(int visibility);

    void saveGrilsGankData();

    String setUrl(int index);
}
