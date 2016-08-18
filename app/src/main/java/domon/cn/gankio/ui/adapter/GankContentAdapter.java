package domon.cn.gankio.ui.adapter;

import android.content.Context;

import com.socks.library.KLog;

import domon.cn.gankio.R;
import domon.cn.gankio.data.GankDateData;

/**
 * Created by Domon on 16-8-11.
 */
public class GankContentAdapter extends BaseRVAdapter<GankDateData> {

    public GankContentAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_home;
    }

    @Override
    protected void onBindDataToView(BaseViewHolder holder, GankDateData gankDateData, int position) {
        KLog.e();
    }
}
