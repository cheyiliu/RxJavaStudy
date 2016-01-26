package com.study.rx.demo;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hsy on 2016/1/22.
 */
public class Create {

    public static final String TAG = "test";

    /**
     * create
     */
    public static void create() {
        Log.i(TAG, "create--->");
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    Log.i(TAG, "create, observable call, onNext" + ", thread id=" + Thread.currentThread().getId());
                    subscriber.onNext(i);
                }
                Log.i(TAG, "create, observable call, onCompleted" + ", thread id=" + Thread.currentThread().getId());
                subscriber.onCompleted();
            }
        });

        observable.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "create, onCompleted" + ", thread id=" + Thread.currentThread().getId());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.i(TAG, "create, onError" + ", thread id=" + Thread.currentThread().getId());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG, "create, onNext" + ", thread id=" + Thread.currentThread().getId());
                    }
                });
    }

    /**
     * from
     */
    public static void from() {
        Log.i(TAG, "from--->");
        String[] strArray = {"1", "a", "c"};
        Observable.from(strArray)
                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.computation()) //vs1
                .observeOn(AndroidSchedulers.mainThread())//vs2
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "from, onCompleted" + ", thread id=" + Thread.currentThread().getId());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.i(TAG, "from, onError" + ", thread id=" + Thread.currentThread().getId());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG, "from, onNext, " + s + ", thread id=" + Thread.currentThread().getId());
                    }
                });
    }

    /**
     * just
     */
    public static void just() {
        Log.i(TAG, "just--->");
        Observable<String> observable = Observable.just("just1", "just2");
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "just, onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                Log.i(TAG, "just, onError");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "just, onNext, " + s);
            }
        });
    }
}
