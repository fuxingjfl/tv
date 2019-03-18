package com.starv.tvindex.model.local.Ipml;

import com.starv.tvindex.model.local.Iinterface.IWatched;
import com.starv.tvindex.model.local.Iinterface.IWatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weizhi on 2016/8/23.
 */
public class ConcreteWatched implements IWatched {
    // 存放观察者
    private List<IWatcher> list = new ArrayList<IWatcher>();
    @Override
    public void addWatcher(IWatcher watcher) {
        list.add(watcher);
    }

    @Override
    public void removeWatcher(IWatcher watcher) {
        list.remove(watcher);
    }

    @Override
    public void notifyWatchers() {
        // 自动调用实际上是主题进行调用的
        for (IWatcher watcher : list){
            watcher.update();
        }
    }
}
