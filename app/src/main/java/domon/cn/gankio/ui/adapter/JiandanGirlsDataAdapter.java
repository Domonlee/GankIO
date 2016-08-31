package domon.cn.gankio.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import domon.cn.gankio.R;
import domon.cn.gankio.ui.activity.ImageViewActivity;

/**
 * Created by Domon on 16-8-30.
 */
public class JiandanGirlsDataAdapter extends BaseRVAdapter<String> {
    private List<Integer> heights = new ArrayList<>();
    private List<String> urls = new ArrayList<>();

    public JiandanGirlsDataAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_girls;
    }

    @Override
    protected void onBindDataToView(BaseViewHolder holder, String strings, int position) {
        if (heights.size() <= position) {
            heights.add((int) (100 + Math.random() * 300));
        }

        if (urls.size() <= position) {
            urls.add(position, strings);
        }

        ViewGroup.LayoutParams params = holder.getView(R.id.item_girls_iv).getLayoutParams();
        params.height = heights.get(position);
        holder.getView(R.id.item_girls_iv).setLayoutParams(params);

        holder.setImageFromUrl(R.id.item_girls_iv, strings);
    }

    @Override
    protected void OnItemClick(int position) {
        super.OnItemClick(position);
        Intent intent = new Intent();
        intent.putExtra("url", urls.get(position - 1));
        intent.setClass(mContext, ImageViewActivity.class);
        mContext.startActivity(intent);
    }
}
