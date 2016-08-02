package com.whizzosoftware.hobson.api.task;

import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.plugin.PluginContext;
import com.whizzosoftware.hobson.api.property.*;
import com.whizzosoftware.hobson.api.task.action.TaskActionClass;
import com.whizzosoftware.hobson.api.task.condition.ConditionClassType;
import com.whizzosoftware.hobson.api.task.condition.TaskConditionClass;

import java.util.*;

public class MockTaskManager implements TaskManager {
    private Map<TaskContext,HobsonTask> createdTasks = new HashMap<>();
    private Map<PropertyContainerClassContext,TaskActionClass> actionClasses = new HashMap<>();
    private Map<PropertyContainerClassContext,TaskConditionClass> conditionClasses = new HashMap<>();
    private Map<String,PropertyContainerSet> actionSets = new HashMap<>();
    private List<TaskContext> taskExecutions = new ArrayList<>();
    private List<String> actionSetExecutions = new ArrayList<>();

    @Override
    public Collection<HobsonTask> getAllTasks(HubContext ctx) {
        return null;
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
    public void publishActionClass(TaskActionClass actionClass) {
        actionClasses.put(actionClass.getContext(), actionClass);
    }

    @Override
    public PropertyContainerSet publishActionSet(HubContext ctx, String name, List<PropertyContainer> actions) {
        String actionSetId = UUID.randomUUID().toString();
        PropertyContainerSet tas = new PropertyContainerSet(actionSetId, actions);
        actionSets.put(actionSetId, tas);
        return tas;
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
    public TaskActionClass getActionClass(PropertyContainerClassContext ctx) {
        return actionClasses.get(ctx);
    }

    @Override
    public PropertyContainerSet getActionSet(HubContext ctx, String actionSetId) {
        return actionSets.get(actionSetId);
    }

    @Override
    public Collection<TaskActionClass> getAllActionClasses(HubContext ctx, boolean applyConstraints) {
        return null;
    }

    @Override
    public Collection<PropertyContainerSet> getAllActionSets(HubContext ctx) {
        return null;
    }

    @Override
    public Collection<TaskConditionClass> getAllConditionClasses(HubContext ctx, ConditionClassType type, boolean applyConstraints) {
        return null;
    }

    @Override
    public void unpublishAllActionClasses(PluginContext ctx) {

    }

    @Override
    public void unpublishAllActionSets(PluginContext ctx) {

    }

    @Override
    public void unpublishAllConditionClasses(PluginContext ctx) {

    }

    @Override
    public void updateTask(TaskContext ctx, String name, String description, List<PropertyContainer> conditions, PropertyContainerSet actionSet) {

    }

    @Override
    public void updateTaskProperties(TaskContext ctx, Map<String, Object> properties) {
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
        HobsonTask task = new HobsonTask(TaskContext.create(ctx, UUID.randomUUID().toString()), name, description, null, conditions, actionSet);
        createdTasks.put(task.getContext(), task);
    }

    @Override
    public void deleteTask(TaskContext ctx) {

    }

    @Override
    public void executeTask(TaskContext ctx) {

    }

    @Override
    public void executeActionSet(HubContext ctx, String actionSetId) {
        actionSetExecutions.add(actionSetId);
    }

    public List<String> getActionSetExecutions() {
        return actionSetExecutions;
    }
}
