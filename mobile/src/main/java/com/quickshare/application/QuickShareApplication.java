package com.quickshare.application;

import android.app.Application;

import dagger.ObjectGraph;
import timber.log.Timber;

public class QuickShareApplication extends Application {

    protected static ObjectGraph objectGraph;
    private static QuickShareApplication quickShareApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.uprootAll();
        quickShareApplication = this;
        objectGraph = ObjectGraph.create(getModules());
        inject(this);
    }

    protected Object[] getModules() {
        Object[] modules = {new QuickShareModule(this)};
        return modules;
    }

    public static void inject(Object object) {
        objectGraph.inject(object);
    }

    public static <T> T get(Class<T> clazz) {
        return objectGraph.get(clazz);
    }
}
