package org.flauschhaus.traces;

import android.app.Application;

import org.flauschhaus.traces.journal.entry.DaoMaster;
import org.flauschhaus.traces.journal.entry.DaoSession;
import org.greenrobot.greendao.database.Database;

public class TracesApplication extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "traces-db");
        Database database = helper.getWritableDb();
        daoSession = new DaoMaster(database).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
