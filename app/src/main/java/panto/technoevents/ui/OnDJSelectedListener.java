package panto.technoevents.ui;

import panto.technoevents.apimodels.djs.DjModel;

public interface onDJSelectedListener {

    void openEventsFragment(DjModel djModel);

    void toDjsFragment();

}