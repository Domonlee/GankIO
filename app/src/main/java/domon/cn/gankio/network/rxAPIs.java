package domon.cn.gankio.network;

import domon.cn.gankio.data.GankContentData;
import domon.cn.gankio.data.GankHistoryData;
import domon.cn.gankio.data.JiandanGirlsData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Domon on 16-9-9.
 */
public interface rxAPIs {
    String[] GankCategory = new String[]{"all", "福利", "Android", "iOS", "拓展资源", "前端", "瞎推荐", "休息视频"};
    String GankBaseUrl = "http://www.gank.io/api/";
    String JianDanBaseUrl = "http://pho.orrindeng.com/pho/getpho/";

    /**
     * 每日数据： http://gank.io/api/day/年/月/日
     */
    @GET("day/{date}")
    Observable<GankContentData> getRxGankInfoData(@Path("date") String date);

    /**
     * 获取发布过干货的日期
     */
    @GET("day/history")
    Observable<GankHistoryData> getRxGankHistoryDate();

    /**
     * http://gank.io/api/data/福利/10/1
     * <p/>
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     */
    @GET("{type}/{index}/{count}")
    Observable<GankContentData> getRxAllGankData(
            @Path("type") String type,
            @Path("index") String index,
            @Path("count") String count);

    @GET("{index}/{count}")
    Observable<JiandanGirlsData> getRxJianDanGirlsDate(
            @Path("index") String index,
            @Path("count") String count);
}
