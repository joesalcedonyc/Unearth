package panto.technoevents.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import panto.technoevents.apimodels.djs.DjModel;
import panto.technoevents.network.DjRepository;

public class EventsViewModel extends ViewModel {



    private void getDjs (){
        DjRepository.getInstance()
                .getAllDjs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DjModel>>() {
                               @Override
                               public void accept(List<DjModel> djModels) throws Exception {

                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });


    }
}
