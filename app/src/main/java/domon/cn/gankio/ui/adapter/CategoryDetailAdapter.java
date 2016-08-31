package domon.cn.gankio.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

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
        holder.getView(R.id.item_category_girls_iv).setVisibility(View.GONE);

        if (mPostions.size() <= position) {
            mPostions.add(position, gankInfoData.getUrl());
        }

        switch (gankInfoData.getType()) {
            case "Android":
                holder.setImage(R.id.item_categroy_iv, R.mipmap.category_android_icon);
                break;
            case "iOS":
                holder.setImage(R.id.item_categroy_iv, R.mipmap.category_ios_icon);
                break;
            case "休息视频":
                holder.setImage(R.id.item_categroy_iv, R.mipmap.category_video_icon);
                break;
            case "福利":
                holder.getView(R.id.item_category_girls_iv).setVisibility(View.VISIBLE);
                holder.setImage(R.id.item_categroy_iv, R.mipmap.category_girls_icon);
                holder.setImageFromUrl(R.id.item_category_girls_iv, gankInfoData.getUrl());
                break;
            case "拓展资源":
                holder.setImage(R.id.item_categroy_iv, R.mipmap.category_more_icon);
                break;
            case "瞎推荐":
                holder.setImage(R.id.item_categroy_iv, R.mipmap.category_other_icon);
                break;
            case "前端":
                holder.setImage(R.id.item_categroy_iv, R.mipmap.category_html_icon);
                break;
            default:
                break;
        }


        holder.setText(R.id.item_categroy_tv, gankInfoData.getType());
        holder.setText(R.id.item_desc_tv, gankInfoData.getDesc());
    }

    @Override
    protected void OnItemClick(int position) {
        super.OnItemClick(position);
        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mPostions.get(position))));
    }
}
