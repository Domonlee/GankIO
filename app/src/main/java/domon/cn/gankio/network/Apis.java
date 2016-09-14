package domon.cn.gankio.network;

/**
 * Created by Domon on 16-8-10.
 */
public class Apis {
    public static String[] GankCategory = new String[]{"all", "福利", "Android", "iOS", "拓展资源", "前端", "瞎推荐", "休息视频"};


    public static String GankBaseUrl = "http://www.gank.io/api/";

    /**
     * http://gank.io/api/data/福利/10/1
     * <p/>
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     */
    public static String GankAllData = GankBaseUrl + "data/";
}
