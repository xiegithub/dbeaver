package org.jkiss.dbeaver.cloud.git.handlers;

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

public class CloudHandler extends AbstractHandler {
    
 
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	    
	    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
	    
	    IHandlerService handlerService = window.getService(IHandlerService.class);
	    ICommandService commandService = window.getService(ICommandService.class);
	    
	    Command shareCommand = commandService.getCommand("org.eclipse.egit.ui.command.shareProject");
	    
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
	    
	    //final IProject projectToShare = ResourcesPlugin.getWorkspace().getRoot().getProject("General");
//	    
//	    Map<String, String> params = new HashMap<>(1);
//	    
//	    params.put(PROJECT_NAME_PARAMETER, "General");
//	    
//	    ExecutionEvent shareEvent = new ExecutionEvent(params, event.getTrigger(), event.getApplicationContext());
//	    
//	    try {
//	        handlerService.executeCommand("shareEvent",shareEvent);
//	        } catch (Exception ex) {
//	            throw new RuntimeException("");
//	            // Give message
//	            }
	    
	    
	    
	    //IHandlerService handlerService = (IHandlerService) getSite().getService(IHandlerService.class);
	    
	    //event.getApplicationContext()
	    
//		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
//		
//		final String projectName = event.getParameter(PROJECT_NAME_PARAMETER);
//        final IProject projectToShare = ResourcesPlugin.getWorkspace()
//                .getRoot().getProject("General");
//        IWorkbench workbench = HandlerUtil.getActiveWorkbenchWindow(event)
//                .getWorkbench();
//
//        final SharingWizard wizard = new SharingWizard();
//        wizard.init(workbench, projectToShare);
//        final Shell shell = HandlerUtil.getActiveShell(event);
//        WizardDialog wizardDialog = new WizardDialog(shell, wizard);
//        wizardDialog.setHelpAvailable(false);
//        wizardDialog.open();
//        return null;
	    
	    return null;
		
	}
}
