package com.cheney.gankjava.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.cheney.gankjava.R;
import com.cheney.gankjava.bean.HomeItem;
import com.cheney.gankjava.databinding.ItemGankBinding;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;

public class HomeAdapter extends ListAdapter<HomeItem,RecyclerView.ViewHolder> {


    private LifecycleOwner lifecycleOwner;

    public HomeAdapter(LifecycleOwner lifecycleOwner) {
        super(new DiffUtil.ItemCallback<HomeItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull HomeItem oldItem, @NonNull HomeItem newItem) {
                return HomeItem.areItemsTheSame(oldItem,newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull HomeItem oldItem, @NonNull HomeItem newItem) {
                return HomeItem.areContentsTheSame(oldItem, newItem);
            }
        });
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case HomeItem.TYPE_BANNER:
                viewHolder = new BannerVH((Banner) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false));
                break;
            case HomeItem.TYPE_GANK:
                viewHolder = new GankVH(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_gank, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HomeItem.TYPE_BANNER:
                BannerVH bannerVH = (BannerVH) holder;
                Banner banner = bannerVH.banner;
                banner.addBannerLifecycleObserver(lifecycleOwner)//添加生命周期观察者
                        .setAdapter(new HomeBannerAdapter(getItem(position).getGankBanners()))
                        .setIndicator(new CircleIndicator(banner.getContext()))
                        .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
                        .start();
                break;
            case HomeItem.TYPE_GANK:
                GankVH gankVH = (GankVH) holder;
                gankVH.binding.setGank(getItem(position).getGank());
                gankVH.binding.executePendingBindings();
                break;
        }
    }


    @Override
    public int getItemViewType(int position) {
        return getItem(position).getItemType();
    }

    public static final class BannerVH extends RecyclerView.ViewHolder {

        private final Banner banner;

        public BannerVH(@NonNull Banner banner) {
            super(banner);
            this.banner = banner;
        }

    }

    public static final class GankVH extends RecyclerView.ViewHolder {

        private final ItemGankBinding binding;

        public GankVH(@NonNull ItemGankBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }
}
