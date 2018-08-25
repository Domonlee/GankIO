package domon.cn.gankio.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import domon.cn.gankio.R;
import domon.cn.gankio.data.GankGirlsData;
import domon.cn.gankio.ui.activity.ImageViewActivity;

/**
 * Created by Domon on 16-8-18.
 */
public class GankGirlsDataAdapter extends BaseRVAdapter<GankGirlsData.ResultsEntity> {
    private List<Integer> heights = new ArrayList<>();
    private List<String> urls = new ArrayList<>();

    public GankGirlsDataAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_girls;
    }

    @Override
    protected void onBindDataToView(BaseViewHolder holder, GankGirlsData.ResultsEntity resultsEntity, int position) {
        if (heights.size() <= position) {
            heights.add((int) (100 + Math.random() * 300));
        }

        if (urls.size() <= position) {
            urls.add(position, resultsEntity.getUrl());
        }
        ViewGroup.LayoutParams params = holder.getView(R.id.item_girls_iv).getLayoutParams();
        params.height = heights.get(position);
        holder.getView(R.id.item_girls_iv).setLayoutParams(params);

        holder.setImageFromUrl(R.id.item_girls_iv, resultsEntity.getUrl());
    }

    @Override
    protected void OnItemClick(int position) {
        super.OnItemClick(position);

        ImageViewActivity.Companion.startActivity(mContext,urls.get(position - 1));
    }
}
