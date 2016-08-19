package domon.cn.gankio.network;

/**
 * Created by Domon on 16-8-10.
 */
public class Apis {
    public static String[] GankCategory = new String[]{"all", "休息视频", "福利", "Android", "iOS", "拓展资源", "前端", "瞎推荐"};


    public static String GankBaseUrl = "http://www.gank.io/api/";
    /**
     * 获取发布过干货的日期
     */
    public static String GankHistoryDates = "http://www.gank.io/api/day/history";

    /**
     * http://gank.io/api/data/福利/10/1
     * <p/>
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     */
    public static String GankAllData = GankBaseUrl + "data/";

    /**
     * 每日数据： http://gank.io/api/day/年/月/日
     */
    public static String GankDataByDay = GankBaseUrl + "day/";


    /**
     * 随机图片
     */
    public static String RandomPicture = "http://lelouchcrgallery.tk/rand";
}
