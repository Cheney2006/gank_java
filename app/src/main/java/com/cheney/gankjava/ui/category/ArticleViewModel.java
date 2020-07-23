package com.cheney.gankjava.ui.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.cheney.gankjava.bean.CategoryType;
import com.cheney.gankjava.bean.Gank;
import com.cheney.gankjava.repository.GankRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ArticleViewModel extends ViewModel {

    private final GankRepository repository;
    private CompositeDisposable disposable;
    private ArticleDataSourceFactory factory;
    public LiveData<Boolean> isLoading = new MutableLiveData<>();
    public LiveData<Throwable> error = new MutableLiveData<>();
    public LiveData<PagedList<Gank>> pagedListLiveData;


    @Inject
    public ArticleViewModel(GankRepository repository) {
        this.repository = repository;
        disposable = new CompositeDisposable();
    }

    public void initType(CategoryType type) {
        factory = new ArticleDataSourceFactory(repository, type, disposable);
        initPaging();
    }

    private void initPaging() {
        //在不足一页时，会自动加载下一页，可控件loadAfter。。。。
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .setInitialLoadSizeHint(10)
                .setPrefetchDistance(2)
                .build();
        pagedListLiveData = new LivePagedListBuilder<>(factory, config).build();

        isLoading = Transformations.switchMap(factory.getDataSourceMutableLiveData(), dataSource -> dataSource.getIsLoading());
        error = Transformations.switchMap(factory.getDataSourceMutableLiveData(), dataSource -> dataSource.getError());
    }


    public void refresh() {
        factory.refresh();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}