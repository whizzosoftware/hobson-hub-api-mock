package com.whizzosoftware.hobson.api.task;

import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.plugin.PluginContext;
import com.whizzosoftware.hobson.api.property.*;
import com.whizzosoftware.hobson.api.task.condition.ConditionClassType;
import com.whizzosoftware.hobson.api.task.condition.TaskConditionClass;

import java.util.*;

public class MockTaskManager implements TaskManager {
    private Map<TaskContext,HobsonTask> createdTasks = new HashMap<>();
    private Map<PropertyContainerClassContext,TaskConditionClass> conditionClasses = new HashMap<>();
    private List<TaskContext> taskExecutions = new ArrayList<>();
    private List<String> actionSetExecutions = new ArrayList<>();

    @Override
    public Collection<HobsonTask> getTasks(HubContext ctx) {
        return createdTasks.values();
    }

    @Override
    public TaskConditionClass getConditionClass(PropertyContainerClassContext ctx) {
        return conditionClasses.get(ctx);
    }

    @Override
    public HobsonTask getTask(TaskContext ctx) {
        return createdTasks.get(ctx);
    }

    @Override
    public void publishConditionClass(TaskConditionClass conditionClass) {
        conditionClasses.put(conditionClass.getContext(), conditionClass);
    }

    @Override
    public void fireTaskTrigger(TaskContext ctx) {
        taskExecutions.add(ctx);
    }

    public List<TaskContext> getTaskExecutions() {
        return taskExecutions;
    }

    @Override
    public Collection<TaskConditionClass> getConditionClasses(HubContext ctx, ConditionClassType type, boolean applyConstraints) {
        return null;
    }

    @Override
    public void updateTask(PluginContext pctx, TaskContext ctx, String name, String description, boolean enabled, List<PropertyContainer> conditions, PropertyContainerSet actionSet) {

    }

    @Override
    public void updateTaskProperties(PluginContext pctx, TaskContext ctx, Map<String, Object> properties) {
        HobsonTask task = createdTasks.get(ctx);
        if (task != null) {
            for (String key : properties.keySet()) {
                task.setProperty(key, properties.get(key));
            }
        }
    }

    public Collection<HobsonTask> getCreatedTasks() {
        return createdTasks.values();
    }

    @Override
    public void createTask(HubContext ctx, String name, String description, List<PropertyContainer> conditions, PropertyContainerSet actionSet) {
        HobsonTask task = new HobsonTask(TaskContext.create(ctx, UUID.randomUUID().toString()), name, description, true, null, conditions, actionSet);
        createdTasks.put(task.getContext(), task);
    }

    @Override
    public void deleteTask(TaskContext ctx) {

    }

    @Override
    public void executeTask(TaskContext ctx) {

    }

    public List<String> getActionSetExecutions() {
        return actionSetExecutions;
    }
}
