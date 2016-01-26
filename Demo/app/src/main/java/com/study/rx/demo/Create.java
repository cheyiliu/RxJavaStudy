package com.study.rx.demo;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hsy on 2016/1/22.
 */
public class Create {

    public static final String TAG = "test";

    public static void from() {
        String[] strArray = {"1", "a", "c"};
        Observable.from(strArray)
                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.computation()) //vs1
                .observeOn(AndroidSchedulers.mainThread())//vs2
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted" + ", thread id=" + Thread.currentThread().getId());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.i(TAG, "onError" + ", thread id=" + Thread.currentThread().getId());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG, "onNext, " + s + ", thread id=" + Thread.currentThread().getId());
                    }
                });
    }
}
