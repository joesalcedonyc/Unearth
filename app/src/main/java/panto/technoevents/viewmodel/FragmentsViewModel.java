package panto.technoevents.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.apimodels.edmtrain.Event;
import panto.technoevents.db.RemoteDataBase;
import panto.technoevents.network.DjRepository;

public class FragmentsViewModel extends ViewModel {
    private final CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<List<DjModel>> djs;
    public MutableLiveData<List<DjModel>> favorites;
    public MutableLiveData<List<Event>> djEvents;

    public FragmentsViewModel() {
        djs = new MutableLiveData<>();
        djEvents = new MutableLiveData<>();
        favorites = new MutableLiveData<>();
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

    public void loadFavorites() {
        favorites.setValue(RemoteDataBase.getInstance()
          .getFavorites());
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
