/*******************************************************************************
 * Copyright (c) 2015 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.plugin;

import com.whizzosoftware.hobson.api.task.HobsonTask;
import com.whizzosoftware.hobson.api.task.TaskContext;
import com.whizzosoftware.hobson.api.task.TaskProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MockTaskProvider implements TaskProvider {
    private List<HobsonTask> createdTasks = new ArrayList<>();

    public List<HobsonTask> getCreatedTasks() {
        return createdTasks;
    }

    public void clearCreatedTasks() {
        createdTasks.clear();
    }

    @Override
    synchronized public void onCreateTasks(Collection<HobsonTask> tasks) {
        createdTasks.addAll(tasks);
        notify();
    }

    @Override
    public void onUpdateTask(HobsonTask task) {

    }

    @Override
    public void onDeleteTask(TaskContext ctx) {

    }
}
