package domon.cn.gankio.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import domon.cn.gankio.R;
import domon.cn.gankio.data.GankInfoData;

/**
 * Created by Domon on 16-8-22.
 */
public class CategoryDetailAdapter extends BaseRVAdapter<GankInfoData> {
    private List<String> mPostions = new ArrayList<>();

    public CategoryDetailAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_categroy;
    }

    @Override
    protected void onBindDataToView(BaseViewHolder holder, GankInfoData gankInfoData, int position) {
        if (mPostions.size() <= position){
            mPostions.add(position,gankInfoData.getUrl());
        }


        holder.setText(R.id.item_categroy_tv, gankInfoData.getType());
        holder.setText(R.id.item_desc_tv, gankInfoData.getDesc());
    }

    @Override
    protected void OnItemClick(int position) {
        super.OnItemClick(position);
        mContext.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(mPostions.get(position))));
    }
}
