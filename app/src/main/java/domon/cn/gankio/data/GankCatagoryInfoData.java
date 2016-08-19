package domon.cn.gankio.data;

import java.util.List;

/**
 * Created by Domon on 16-8-18.
 */
public class GankCatagoryInfoData {


    private List<GankInfoData> Android;

    private List<GankInfoData> iOS;

    private List<GankInfoData> 休息视频;

    private List<GankInfoData> 拓展资源;

    private List<GankInfoData> 瞎推荐;

    private List<GankInfoData> 福利;

    public List<GankInfoData> getAndroid() {
        return Android;
    }

    public void setAndroid(List<GankInfoData> android) {
        Android = android;
    }

    public List<GankInfoData> getiOS() {
        return iOS;
    }

    public void setiOS(List<GankInfoData> iOS) {
        this.iOS = iOS;
    }

    public List<GankInfoData> get休息视频() {
        return 休息视频;
    }

    public void set休息视频(List<GankInfoData> 休息视频) {
        this.休息视频 = 休息视频;
    }

    public List<GankInfoData> get拓展资源() {
        return 拓展资源;
    }

    public void set拓展资源(List<GankInfoData> 拓展资源) {
        this.拓展资源 = 拓展资源;
    }

    public List<GankInfoData> get瞎推荐() {
        return 瞎推荐;
    }

    public void set瞎推荐(List<GankInfoData> 瞎推荐) {
        this.瞎推荐 = 瞎推荐;
    }

    public List<GankInfoData> get福利() {
        return 福利;
    }

    public void set福利(List<GankInfoData> 福利) {
        this.福利 = 福利;
    }
}
