package domon.cn.gankio.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import domon.cn.gankio.R;
import domon.cn.gankio.data.GankDateData;

/**
 * Created by Domon on 16-8-11.
 */
public class GankContentAdapter extends BaseRVAdapter<GankDateData> {
    private String lastType;
    private List<String> urls = new ArrayList<>();

    public GankContentAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_home;
    }

    @Override
    protected void onBindDataToView(BaseViewHolder holder, GankDateData gankDateData, int position) {
        if (position == 0) {
            holder.getView(R.id.item_categroy_tv).setVisibility(View.VISIBLE);
        } else {
            boolean isNextCategroy = lastType.equals(gankDateData.getType());
            if (isNextCategroy) {
                holder.getView(R.id.item_categroy_tv).setVisibility(View.GONE);
            } else {
                holder.getView(R.id.item_categroy_tv).setVisibility(View.VISIBLE);
            }
        }
        lastType = gankDateData.getType();
        holder.setText(R.id.item_categroy_tv, gankDateData.getType());
        holder.setText(R.id.item_desc_tv, gankDateData.getDesc());

        if (urls.size() <= position) {
            urls.add(gankDateData.getUrl());
        }
    }

    @Override
    protected void OnItemClick(int position) {
        Uri uri = Uri.parse(urls.get(position));
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(i);

        KLog.e(position);
    }
}
