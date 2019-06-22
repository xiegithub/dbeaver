/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2019 Serge Rider (serge@jkiss.org)
 * Copyright (C) 2019 Andrew Khitrin (ahitrin@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jkiss.dbeaver.team.git.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerService;

public class PushHandler extends AbstractHandler{

    private static final String CMD_PUSH = "org.eclipse.egit.ui.team.Push";

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        
        IHandlerService handlerService = window.getService(IHandlerService.class);
        ICommandService commandService = window.getService(ICommandService.class);
        
        Command pushCommand = commandService.getCommand(CMD_PUSH);
        
        final IProject projectToCommit = ResourcesPlugin.getWorkspace().getRoot().getProject("General");
        
        ISelection prjSelection = new StructuredSelection(projectToCommit);
        
        IEvaluationContext context = new EvaluationContext(
                handlerService.createContextSnapshot(false), prjSelection);

        
        //pushCommand.getHandler().execute(event);
        
        try {
            handlerService.executeCommand(CMD_PUSH, null);
        } catch (Exception ex) {
            throw new RuntimeException("error execute Push");
        }
        
        return null;
    }

}
