package com.study.rx.demo;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subjects.PublishSubject;

/**
 * Created by hsy on 2016/1/26.
 */
public class Subject {
    public static final String TAG = "test";

    public static void test() {
        Log.i(TAG, "PublishSubject test--->");
        final PublishSubject<String> stringPublishSubject = PublishSubject.create();
        Subscription subscriptionPrint = stringPublishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "PublishSubject test, onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "PublishSubject test, onError, " + e);
            }

            @Override
            public void onNext(String message) {
                Log.i(TAG, "PublishSubject test, onNext, " + message);
            }
        });

        // vs1
        stringPublishSubject.onNext("Hello World");

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).doOnCompleted(new Action0() {
            @Override
            public void call() {
                Log.i(TAG, "PublishSubject test, doOnCompleted");
                // vs2
                stringPublishSubject.onNext("complete");
            }
        }).subscribe();
    }
}
