package domon.cn.gankio.ui.adapter;

import android.content.Context;

import domon.cn.gankio.R;
import domon.cn.gankio.data.GankInfoData;

/**
 * Created by Domon on 16-8-22.
 */
public class CategoryDetailAdapter extends BaseRVAdapter<GankInfoData> {

    public CategoryDetailAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_categroy;
    }

    @Override
    protected void onBindDataToView(BaseViewHolder holder, GankInfoData gankInfoData, int position) {
        holder.setText(R.id.item_categroy_tv, gankInfoData.getType());
        holder.setText(R.id.item_desc_tv, gankInfoData.getDesc());
    }
}
