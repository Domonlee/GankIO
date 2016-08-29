package domon.cn.gankio.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import domon.cn.gankio.R;
import domon.cn.gankio.data.GankInfoData;

/**
 * Created by Domon on 16-8-11.
 */
public class GankContentAdapter extends BaseRVAdapter<GankInfoData> {
    private String lastType;
    private List<String> urls = new ArrayList<>();
    private boolean isNextCategroy = false;

    public GankContentAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_home;
    }

    @Override
    protected void onBindDataToView(BaseViewHolder holder, final GankInfoData gankInfoData, int position) {
        holder.getView(R.id.item_category_girls_iv).setVisibility(View.GONE);
        holder.getView(R.id.item_desc_tv).setVisibility(View.VISIBLE);
        holder.getView(R.id.item_categroy_tv).setVisibility(View.VISIBLE);


        // TODO: 16-8-29 data refresh error
        if (isImage(gankInfoData.getUrl())) {
            holder.getView(R.id.item_category_girls_iv).setVisibility(View.VISIBLE);
            holder.getView(R.id.item_desc_tv).setVisibility(View.GONE);
            holder.setImageFromUrl(R.id.item_category_girls_iv, gankInfoData.getUrl());
        }

//        if (holder.getView(R.id.item_categroy_iv).getVisibility() == View.VISIBLE) {
//            switch (lastType) {
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
                holder.setImage(R.id.item_categroy_iv, R.mipmap.ic_launcher);
                break;
            default:
                break;
            }
//        }

        if (position == 0) {
            holder.getView(R.id.item_categroy_tv).setVisibility(View.VISIBLE);
            holder.getView(R.id.item_categroy_iv).setVisibility(View.VISIBLE);
        } else {
            isNextCategroy = lastType.equals(gankInfoData.getType());
            KLog.e(gankInfoData.getType(), isNextCategroy);
            if (isNextCategroy) {
                holder.getView(R.id.item_categroy_tv).setVisibility(View.GONE);
                holder.getView(R.id.item_categroy_iv).setVisibility(View.GONE);
            } else {
                holder.getView(R.id.item_categroy_tv).setVisibility(View.VISIBLE);
                holder.getView(R.id.item_categroy_iv).setVisibility(View.VISIBLE);
            }
        }

        lastType = gankInfoData.getType();

        holder.setText(R.id.item_categroy_tv, gankInfoData.getType());
        holder.setText(R.id.item_desc_tv, gankInfoData.getDesc());

        if (urls.size() <= position) {
            isNextCategroy = true;
            urls.add(gankInfoData.getUrl());
        }
    }

    @Override
    protected void OnItemClick(int position) {
        Uri uri = Uri.parse(urls.get(position));
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(i);

        KLog.e(position);
    }

    private boolean isImage(String url) {
        return url.endsWith(".jpg") || url.endsWith(".png");
    }
}
