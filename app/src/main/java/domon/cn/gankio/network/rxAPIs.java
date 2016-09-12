package domon.cn.gankio.network;

import domon.cn.gankio.data.GankContentData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Domon on 16-9-9.
 */
public interface rxAPIs {

    @GET("day/{date}")
    Call<GankContentData> getRxGankInfoData(@Path("date") String date);

    @GET("day/history")
    Call<String> getRxGankHistoryDate();
}
