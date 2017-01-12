package domon.cn.gankio.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import domon.cn.gankio.R;
import domon.cn.gankio.data.GankInfoData;
import domon.cn.gankio.ui.activity.ImageViewActivity;
import domon.cn.gankio.utils.OnLoadMoreListener;

/**
 * Created by Domon on 16-8-11.
 */
public class GankContentAdapter extends BaseRVAdapter<GankInfoData> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private List<String> urls = new ArrayList<>();
    private List<GankInfoData> mDataList = new ArrayList<>();

    private OnLoadMoreListener mListener;
    private boolean isLoading;
    private int mVisibleThreshold = 5;
    private int mLastVisibleItem, mTotalItemCount;
    private Context mContext;

    public GankContentAdapter(Context mContext) {
        super(mContext);
    }

    public GankContentAdapter(Context mContext, RecyclerView view, List<GankInfoData> datas) {
        super(datas,mContext);
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) view.getLayoutManager();
        view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mTotalItemCount = linearLayoutManager.getItemCount();
                mLastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();

                if (!isLoading && mTotalItemCount <= (mLastVisibleItem + mVisibleThreshold)) {
                    if (mListener != null) {
                        mListener.onLoadMore();
                    }
                }
            }
        });
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_home;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mListener) {
        this.mListener = mListener;
    }

    public void setLoaded() {
        isLoading = false;
    }


    @Override
    protected void onBindDataToView(BaseViewHolder holder, final GankInfoData gankInfoData, int position) {
        holder.getView(R.id.item_category_girls_iv).setVisibility(View.GONE);
        holder.getView(R.id.item_desc_tv).setVisibility(View.VISIBLE);
        holder.getView(R.id.item_categroy_tv).setVisibility(View.VISIBLE);
        holder.getView(R.id.item_categroy_iv).setVisibility(View.VISIBLE);

        if (gankInfoData.getType().equals("sameCategory")) {
            holder.getView(R.id.item_categroy_tv).setVisibility(View.GONE);
            holder.getView(R.id.item_categroy_iv).setVisibility(View.GONE);
        }

        if (isImage(gankInfoData.getUrl())) {
            holder.getView(R.id.item_category_girls_iv).setVisibility(View.VISIBLE);
            holder.getView(R.id.item_desc_tv).setVisibility(View.GONE);
            holder.setImageFromUrl(R.id.item_category_girls_iv, gankInfoData.getUrl());
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

        if (urls.size() <= position) {
            urls.add(gankInfoData.getUrl());
        }
    }

    @Override
    protected void OnItemClick(int position) {
        if (isImage(urls.get(position))) {
            ImageViewActivity.startActivity(mContext, urls.get(position));
        } else {
            Uri uri = Uri.parse(urls.get(position));
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            mContext.startActivity(i);
        }
    }

    private boolean isImage(String url) {
        return url.endsWith(".jpg") || url.endsWith(".png");
    }
}
