package com.cheney.gankjava.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cheney.gankjava.R;
import com.cheney.gankjava.bean.GankBanner;
import com.cheney.gankjava.databinding.ItemBannerItemBinding;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class HomeBannerAdapter extends BannerAdapter<GankBanner, HomeBannerAdapter.BannerViewHolder> {

    public HomeBannerAdapter(List<GankBanner> datas) {
        super(datas);
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {

        return new BannerViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_banner_item, parent, false));
    }

    @Override
    public void onBindView(BannerViewHolder holder, GankBanner data, int position, int size) {
        holder.binding.setGankBanner(getItem(position));
    }

    public GankBanner getItem(int position) {
        return getData(position);
    }

    public static final class BannerViewHolder extends RecyclerView.ViewHolder {

        ItemBannerItemBinding binding;

        public BannerViewHolder(@NonNull ItemBannerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
