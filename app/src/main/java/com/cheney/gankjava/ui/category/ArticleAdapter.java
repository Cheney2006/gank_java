package com.cheney.gankjava.ui.category;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cheney.gankjava.R;
import com.cheney.gankjava.bean.Gank;
import com.cheney.gankjava.databinding.ItemGankBinding;

public class ArticleAdapter extends PagedListAdapter<Gank, ArticleAdapter.GankVH> {


    public ArticleAdapter() {
        super(new DiffUtil.ItemCallback<Gank>() {
            @Override
            public boolean areItemsTheSame(@NonNull Gank oldItem, @NonNull Gank newItem) {
                return oldItem.getGankId()==newItem.getGankId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Gank oldItem, @NonNull Gank newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public ArticleAdapter.GankVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GankVH(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_gank, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GankVH holder, int position) {
        holder.binding.setGank(getItem(position));
        holder.binding.executePendingBindings();
    }



    public static final class GankVH extends RecyclerView.ViewHolder {
        private final ItemGankBinding binding;

        public GankVH(@NonNull ItemGankBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
