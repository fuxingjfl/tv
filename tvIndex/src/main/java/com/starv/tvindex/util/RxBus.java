//
//package com.starv.tvindex.util;
//
//import rx.Observable;
//import rx.subjects.PublishSubject;
//import rx.subjects.SerializedSubject;
//import rx.subjects.Subject;
//
///**
// * Created by weizhi on 2016/10/31.
// */
//public class RxBus {
//    private static volatile RxBus sRxBus;
//    // 主题
//    private final Subject<Object, Object> mBus;
//
//    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
//    public RxBus() {
//        mBus = new SerializedSubject<>(PublishSubject.create());
//    }
//
//    // 单例RxBus
//    public static RxBus getInstance() {
//        if (sRxBus == null) {
//            synchronized (RxBus.class) {
//                if (sRxBus == null) {
//                    sRxBus = new RxBus();
//                }
//            }
//        }
//        return sRxBus;
//    }
//
//    // 提供了一个新的事件
//    /**
//     * 注意：timer计时器线程发送事件收不到
//     * */
//    public void post(Object o) {
//        mBus.onNext(o);
//    }
//
//    public Subject getSubject(){
//        return mBus;
//    }
//
//    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
//    public <T> Observable<T> toObservable(Class<T> eventType) {
//        return mBus.ofType(eventType);
////        ofType = filter + cast
////        return mBus.filter(new Func1<Object, Boolean>() {
////            @Override
////            public Boolean call(Object o) {
////                return eventType.isInstance(o);
////            }
////        }) .cast(eventType);
//    }
//}
