package com.peakmain.baselibrary.launchstarter.utils;

import android.os.Looper;
import android.os.MessageQueue;

import com.peakmain.baselibrary.launchstarter.task.DispatchRunnable;
import com.peakmain.baselibrary.launchstarter.task.Task;

import java.util.LinkedList;
import java.util.Queue;
/**
 * author ：Peakmain
 * version : 1.0
 * createTime：2019/4/15
 * mail:2726449200@qq.com
 * describe：
 */
public class DelayInitDispatcher {

    private Queue<Task> mDelayTasks = new LinkedList<>();

    private MessageQueue.IdleHandler mIdleHandler = new MessageQueue.IdleHandler() {
        @Override
        public boolean queueIdle() {
            if(mDelayTasks.size()>0){
                Task task = mDelayTasks.poll();
                new DispatchRunnable(task).run();
            }
            return !mDelayTasks.isEmpty();
        }
    };

    public DelayInitDispatcher addTask(Task task){
        mDelayTasks.add(task);
        return this;
    }

    public void start(){
        Looper.myQueue().addIdleHandler(mIdleHandler);
    }

}
