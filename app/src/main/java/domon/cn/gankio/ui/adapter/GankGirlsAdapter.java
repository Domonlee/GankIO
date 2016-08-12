package domon.cn.gankio.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.gankio.R;
import domon.cn.gankio.data.GankGirlsData;

/**
 * Created by Domon on 16-8-11.
 */
public class GankGirlsAdapter extends RecyclerView.Adapter<GankGirlsAdapter.GankGirlsViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private GankGirlsData gankGirlsData;
    private List<Integer> heights;

    public GankGirlsAdapter(Context context, GankGirlsData gankGirlsData) {
        this.gankGirlsData = gankGirlsData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        getRandomHeight(this.gankGirlsData);
    }

    private void getRandomHeight(GankGirlsData gankGirlsData) {
        heights = new ArrayList<>();
        for (int i = 0; i < gankGirlsData.getResults().size(); i++) {
            heights.add((int) (200 + Math.random() * 400));
        }
    }

    @Override
    public GankGirlsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GankGirlsViewHolder(layoutInflater.inflate(R.layout.item_girls, parent, false));
    }

    @Override
    public void onBindViewHolder(GankGirlsViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.height = heights.get(position);
        holder.imageView.setLayoutParams(params);

        Glide.with(context)
                .load(gankGirlsData.getResults().get(position).getUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return gankGirlsData == null ? 0 : gankGirlsData.getResults().size();
    }


    public class GankGirlsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_girls_iv)
        ImageView imageView;

        public GankGirlsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
