package domon.cn.gankio.data;

import java.util.List;

/**
 * Created by Domon on 16-8-18.
 */
public class GankCatagoryInfoData {


    private List<GankDateData> Android;

    private List<GankDateData> iOS;

    private List<GankDateData> 休息视频;

    private List<GankDateData> 拓展资源;

    private List<GankDateData> 瞎推荐;

    private List<GankDateData> 福利;

    public List<GankDateData> getAndroid() {
        return Android;
    }

    public void setAndroid(List<GankDateData> android) {
        Android = android;
    }

    public List<GankDateData> getiOS() {
        return iOS;
    }

    public void setiOS(List<GankDateData> iOS) {
        this.iOS = iOS;
    }

    public List<GankDateData> get休息视频() {
        return 休息视频;
    }

    public void set休息视频(List<GankDateData> 休息视频) {
        this.休息视频 = 休息视频;
    }

    public List<GankDateData> get拓展资源() {
        return 拓展资源;
    }

    public void set拓展资源(List<GankDateData> 拓展资源) {
        this.拓展资源 = 拓展资源;
    }

    public List<GankDateData> get瞎推荐() {
        return 瞎推荐;
    }

    public void set瞎推荐(List<GankDateData> 瞎推荐) {
        this.瞎推荐 = 瞎推荐;
    }

    public List<GankDateData> get福利() {
        return 福利;
    }

    public void set福利(List<GankDateData> 福利) {
        this.福利 = 福利;
    }
}
