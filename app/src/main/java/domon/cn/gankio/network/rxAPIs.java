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
    String GankBaseUrl = "http://www.gank.io/api/";
    String JianDanBaseUrl = "http://pho.orrindeng.com/pho/getpho/";

    @GET("day/{date}")
    Observable<GankContentData> getRxGankInfoData(@Path("date") String date);

    @GET("day/history")
    Observable<GankHistoryData> getRxGankHistoryDate();

    @GET("{index}/{count}")
    Observable<JiandanGirlsData> getRxJianDanGirlsDate(
            @Path("index") String index,
            @Path("count") String count);
}
