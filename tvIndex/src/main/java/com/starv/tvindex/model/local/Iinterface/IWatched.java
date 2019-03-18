package com.starv.tvindex.model.local.Iinterface;

/**
 * Created by weizhi on 2016/8/23.
 * 被订阅者(发生任何变化通知所有订阅者)
 */
public interface IWatched {
    public void addWatcher(IWatcher watcher);

    public void removeWatcher(IWatcher watcher);

    public void notifyWatchers();
}
