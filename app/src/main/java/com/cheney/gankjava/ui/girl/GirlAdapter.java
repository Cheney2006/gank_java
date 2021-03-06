package com.cheney.gankjava.ui.girl;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cheney.gankjava.R;
import com.cheney.gankjava.bean.Gank;
import com.cheney.gankjava.databinding.ItemGirlBinding;

import javax.inject.Inject;

public class GirlAdapter extends PagedListAdapter<Gank, GirlAdapter.GirlVH> {


    protected GirlAdapter() {
        super(new DiffUtil.ItemCallback<Gank>() {
            @Override
            public boolean areItemsTheSame(@NonNull Gank oldItem, @NonNull Gank newItem) {
                return oldItem.getGankId() == newItem.getGankId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Gank oldItem, @NonNull Gank newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public GirlVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GirlVH(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_girl, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GirlVH holder, int position) {
        holder.binding.setGank(getItem(position));
        holder.binding.executePendingBindings();
    }

    public static final class GirlVH extends RecyclerView.ViewHolder {

        private ItemGirlBinding binding;

        public GirlVH(@NonNull ItemGirlBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
