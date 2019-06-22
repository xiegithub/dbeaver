package org.jkiss.dbeaver.team.git.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.Parameterization;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerService;

public class ShareHandler extends AbstractHandler {
    
 
	/**
     * 
     */
    private static final String CMD_SHARE = "org.eclipse.egit.ui.command.shareProject";

    @Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	    
	    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
	    
	    IHandlerService handlerService = window.getService(IHandlerService.class);
	    ICommandService commandService = window.getService(ICommandService.class);
	    
	    Command shareCommand = commandService.getCommand(CMD_SHARE);
	    
	    Parameterization[] params = new Parameterization[1];
	    
	    try {
            params[0] = new Parameterization(shareCommand.getParameters()[0], "General");
        } catch (NotDefinedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    

	    ParameterizedCommand pshareCommand = new ParameterizedCommand(shareCommand,
	            params);
	    
        try {
            handlerService.executeCommand(pshareCommand, null);
        } catch (Exception ex) {
            throw new RuntimeException("error execute share");
            // Give message
        }
	    
	    return null;
		
	}
}
