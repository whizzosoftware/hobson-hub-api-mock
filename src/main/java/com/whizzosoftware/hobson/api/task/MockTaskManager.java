package com.whizzosoftware.hobson.api.task;

import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.plugin.PluginContext;
import com.whizzosoftware.hobson.api.property.*;

import java.util.*;

public class MockTaskManager implements TaskManager {
    private Map<String,HobsonTask> publishedTasks = new HashMap<>();
    private Map<PropertyContainerClassContext,PropertyContainerClass> actionClasses = new HashMap<>();
    private Map<PropertyContainerClassContext,PropertyContainerClass> conditionClasses = new HashMap<>();
    private Map<String,PropertyContainerSet> actionSets = new HashMap<>();
    private List<String> actionSetExecutions = new ArrayList<>();

    @Override
    public Collection<HobsonTask> getAllTasks(HubContext ctx) {
        return null;
    }

    @Override
    public PropertyContainerClass getConditionClass(PropertyContainerClassContext ctx) {
        return conditionClasses.get(ctx);
    }

    @Override
    public HobsonTask getTask(TaskContext ctx) {
        return publishedTasks.get(ctx.toString());
    }

    @Override
    public void publishActionClass(PropertyContainerClassContext context, String name, List<TypedProperty> properties) {
        actionClasses.put(context, new PropertyContainerClass(context, name, properties));
    }

    @Override
    public PropertyContainerSet publishActionSet(HubContext ctx, String name, List<PropertyContainer> actions) {
        String actionSetId = UUID.randomUUID().toString();
        PropertyContainerSet tas = new PropertyContainerSet(actionSetId, actions);
        actionSets.put(actionSetId, tas);
        return tas;
    }

    @Override
    public void publishConditionClass(PropertyContainerClassContext ctx, String name, List<TypedProperty> properties) {
        conditionClasses.put(ctx, new PropertyContainerClass(ctx, name, properties));
    }

    @Override
    public void executeTask(TaskContext ctx) {

    }

    @Override
    public PropertyContainerClass getActionClass(PropertyContainerClassContext ctx) {
        return actionClasses.get(ctx);
    }

    @Override
    public PropertyContainerSet getActionSet(HubContext ctx, String actionSetId) {
        return actionSets.get(actionSetId);
    }

    @Override
    public Collection<PropertyContainerClass> getAllActionClasses(HubContext ctx) {
        return null;
    }

    @Override
    public Collection<PropertyContainerSet> getAllActionSets(HubContext ctx) {
        return null;
    }

    @Override
    public Collection<PropertyContainerClass> getAllConditionClasses(HubContext ctx) {
        return null;
    }

    @Override
    public void publishTask(HobsonTask task) {
        publishedTasks.put(task.getContext().toString(), task);
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

    public Collection<HobsonTask> getPublishedTasks() {
        return publishedTasks.values();
    }

    @Override
    public void unpublishAllTasks(PluginContext ctx) {
        publishedTasks.clear();
    }

    @Override
    public void unpublishTask(TaskContext ctx) {

    }

    @Override
    public void updateTask(TaskContext ctx, String name, String description, PropertyContainerSet conditionSet, PropertyContainerSet actionSet) {

    }

    @Override
    public void createTask(HubContext ctx, String name, String description, PropertyContainerSet conditionSet, PropertyContainerSet actionSet) {

    }

    @Override
    public void deleteTask(TaskContext ctx) {

    }

    @Override
    public void executeActionSet(HubContext ctx, String actionSetId) {
        actionSetExecutions.add(actionSetId);
    }

    public List<String> getActionSetExecutions() {
        return actionSetExecutions;
    }
}
