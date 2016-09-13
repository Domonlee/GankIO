package domon.cn.gankio.network;

import domon.cn.gankio.data.GankContentData;
import domon.cn.gankio.data.GankHistoryData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Domon on 16-9-9.
 */
public interface rxAPIs {

    @GET("day/{date}")
    Observable<GankContentData> getRxGankInfoData(@Path("date") String date);

    @GET("day/history")
    Observable<GankHistoryData> getRxGankHistoryDate();
}
