/*******************************************************************************
 * Copyright (c) 2015 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.plugin;

import com.whizzosoftware.hobson.api.task.TaskContext;
import com.whizzosoftware.hobson.api.task.TaskProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MockTaskProvider implements TaskProvider {
    private List<TaskContext> createdTasks = new ArrayList<>();

    public List<TaskContext> getCreatedTasks() {
        return createdTasks;
    }

    public void clearCreatedTasks() {
        createdTasks.clear();
    }

    @Override
    synchronized public void onRegisterTasks(Collection<TaskContext> tasks) {
        createdTasks.addAll(tasks);
        notify();
    }

    @Override
    public void onCreateTask(TaskContext ctx) {
        createdTasks.add(ctx);
        notify();
    }

    @Override
    public void onUpdateTask(TaskContext ctx) {

    }

    @Override
    public void onDeleteTask(TaskContext ctx) {

    }
}
