package com.cheney.gankjava.bean;

import androidx.annotation.NonNull;

import java.util.List;

public class HomeItem {
    public static final int TYPE_BANNER = 1;
    public static final int TYPE_GANK = 2;

    private int itemType;

    private List<GankBanner> gankBanners;

    private Gank gank;

    public HomeItem(List<GankBanner> gankBanners) {
        this.gankBanners = gankBanners;
        itemType = TYPE_BANNER;
    }

    public HomeItem(Gank gank) {
        this.gank = gank;
        itemType = TYPE_GANK;
    }

    public void setGankBanners(List<GankBanner> gankBanners) {
        this.gankBanners = gankBanners;
        itemType = TYPE_BANNER;
    }

    public void setGank(Gank gank) {
        this.gank = gank;
        itemType = TYPE_GANK;
    }

    public int getItemType() {
        return itemType;
    }

    public List<GankBanner> getGankBanners() {
        return gankBanners;
    }

    public Gank getGank() {
        return gank;
    }

    public static boolean areItemsTheSame(@NonNull HomeItem oldItem, @NonNull HomeItem newItem) {
        boolean isTheSame = false;
        if (oldItem.itemType == newItem.itemType) {
            switch (oldItem.itemType) {
                case TYPE_BANNER:
                    if (oldItem.gankBanners != null && newItem.gankBanners != null && oldItem.gankBanners.size() == newItem.gankBanners.size()) {
                        isTheSame = true;
                    }
                    break;
                case TYPE_GANK:
                    if (oldItem.getGank() != null && newItem.getGank() != null && oldItem.getGank().getGankId() == newItem.getGank().getGankId()) {
                        isTheSame = true;
                    }
                    break;
            }
        }
        return isTheSame;
    }

    public static boolean areContentsTheSame(@NonNull HomeItem oldItem, @NonNull HomeItem newItem) {
        boolean isTheSame = false;
        if (oldItem.itemType == newItem.itemType) {
            switch (oldItem.itemType) {
                case TYPE_BANNER:
                    if (oldItem.gankBanners != null && newItem.gankBanners != null && oldItem.gankBanners.size() == newItem.gankBanners.size()) {
                        for (int i = 0; i < oldItem.gankBanners.size(); i++) {
                            if (!oldItem.gankBanners.get(i).equals(newItem.gankBanners.get(i))) {
                                return false;
                            }
                        }
                        isTheSame = true;
                    }
                    break;
                case TYPE_GANK:
                    if (oldItem.getGank().equals(newItem.getGank())) {
                        isTheSame = true;
                    }
                    break;
            }
        }
        return isTheSame;
    }
}
