package com.whizzosoftware.hobson.api.task;

import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.plugin.PluginContext;

import java.util.*;

public class MockTaskManager implements TaskManager {
    private Map<String,HobsonTask> publishedTasks = new HashMap<>();

    @Override
    public void addTask(PluginContext ctx, Object config) {

    }

    @Override
    public Collection<HobsonTask> getAllTasks(HubContext ctx) {
        return null;
    }

    @Override
    public HobsonTask getTask(TaskContext ctx) {
        return publishedTasks.get(ctx.toString());
    }

    @Override
    public void executeTask(TaskContext ctx) {

    }

    @Override
    public void publishTask(HobsonTask task) {
        publishedTasks.put(task.getContext().toString(), task);
    }

    public Collection<HobsonTask> getPublishedTasks() {
        return publishedTasks.values();
    }

    @Override
    public void unpublishAllTasks(PluginContext ctx) {
        publishedTasks.clear();
    }

    @Override
    public void updateTask(TaskContext ctx, Object config) {

    }

    @Override
    public void deleteTask(TaskContext ctx) {

    }
}
