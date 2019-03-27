package panto.technoevents.viewmodel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.apimodels.edmtrain.Event;
import panto.technoevents.network.DjRepository;

public class FragmentsViewModel extends ViewModel {
    private final CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<List<DjModel>> djs;
    public MutableLiveData<List<Event>> djEvents;

    public FragmentsViewModel() {
        djs = new MutableLiveData<>();
        djEvents = new MutableLiveData<>();
    }

    public void loadDjs() {
        disposable.add(DjRepository.getInstance()
                .getAllDjs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(djModels -> djs.setValue(djModels)));
    }

    public void loadEvents(int id) {
        disposable.add(DjRepository.getInstance()
                .getAllDjEvents(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(events -> djEvents.setValue(events)));
    }
    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
