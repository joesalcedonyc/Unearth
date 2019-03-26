package panto.technoevents.viewmodel;


import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.network.DjRepository;

public class DjsFragmentViewModel extends ViewModel {
    private final CompositeDisposable disposable = new CompositeDisposable();

    public MutableLiveData<List<DjModel>> djs;



    public void loadDjs() {

        disposable.add(DjRepository.getInstance()
                .getAllDjs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DjModel>>() {
                    @Override
                    public void accept(List<DjModel> djModels) throws Exception {
                        djs.setValue(djModels);
                    }
                }));

    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
