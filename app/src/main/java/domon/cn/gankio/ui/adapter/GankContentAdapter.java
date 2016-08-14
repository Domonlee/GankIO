package domon.cn.gankio.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.R;
import domon.cn.gankio.data.GankContentData;

/**
 * Created by Domon on 16-8-11.
 */
public class GankContentAdapter extends RecyclerView.Adapter<GankContentAdapter.HomeItemViewHolder> {
    private LayoutInflater layoutInflater;
    private final Context context;
    private GankContentData gankContentData;

    public GankContentAdapter(Context context, GankContentData gankContentData) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.gankContentData = gankContentData;
    }

    @Override
    public HomeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeItemViewHolder(layoutInflater.inflate(R.layout.item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeItemViewHolder holder, int position) {
        // TODO: 16-8-11
        holder.descTv.setText(gankContentData.getResults().getAndroid().get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return gankContentData == null ? 0 : gankContentData.getResults().getAndroid().size();
    }

    public class HomeItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_categroy_tv)
        TextView categroyTv;
        @Bind(R.id.item_desc_tv)
        TextView descTv;

        public HomeItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
