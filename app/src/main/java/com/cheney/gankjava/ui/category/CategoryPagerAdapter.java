package com.cheney.gankjava.ui.category;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.cheney.gankjava.bean.CategoryType;

import java.util.List;

public class CategoryPagerAdapter extends FragmentStateAdapter {

    List<CategoryType> categoryTypes;

    public CategoryPagerAdapter(@NonNull Fragment fragment, List<CategoryType> categoryTypes) {
        super(fragment);
        this.categoryTypes = categoryTypes;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ArticleFragment.newInstance(categoryTypes.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryTypes.size();
    }

}
