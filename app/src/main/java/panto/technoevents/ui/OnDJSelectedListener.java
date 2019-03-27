package panto.technoevents.ui;

import panto.technoevents.apimodels.djs.DjModel;

public interface OnDJSelectedListener {

    void openEventsFragment(DjModel djModel);

    void toDjsFragment();

}
