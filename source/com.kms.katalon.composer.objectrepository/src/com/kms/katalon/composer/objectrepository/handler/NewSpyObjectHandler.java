package com.kms.katalon.composer.objectrepository.handler;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;

import com.kms.katalon.composer.components.log.LoggerSingleton;
import com.kms.katalon.composer.components.tree.ITreeEntity;
import com.kms.katalon.composer.objectrepository.constant.StringConstants;
import com.kms.katalon.controller.ProjectController;
import com.kms.katalon.entity.folder.FolderEntity;
import com.kms.katalon.entity.folder.FolderEntity.FolderType;
import com.kms.katalon.entity.repository.WebElementEntity;
import com.kms.katalon.objectspy.dialog.NewObjectSpyDialog;

public class NewSpyObjectHandler {

    @Inject
    private IEventBroker eventBroker;

    private NewObjectSpyDialog objectSpyDialog;

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell) {
        openDialogAndAddObject(activeShell, null);
    }

    private void openDialogAndAddObject(Shell activeShell, Object[] selectedObjects) {

        try {
            if (objectSpyDialog == null || objectSpyDialog.isDisposed()) {
                try {
                    Shell shell = getShell(activeShell);
                    objectSpyDialog = new NewObjectSpyDialog(shell, LoggerSingleton.getInstance().getLogger(),
                            eventBroker);
                } catch (Exception e) {
                    MessageDialog.openError(activeShell, StringConstants.ERROR_TITLE, e.getMessage());
                }
                objectSpyDialog.setBlockOnOpen(false);
            }
            objectSpyDialog.open();

            if (selectedObjects != null) {
                objectSpyDialog.addObjectsFromObjectRepository(selectedObjects);
            }
        } catch (Exception e) {
            if (objectSpyDialog != null) {
                objectSpyDialog.stop();
                objectSpyDialog.close();
            }
            LoggerSingleton.logError(e);
            MessageDialog.openError(activeShell, StringConstants.ERROR_TITLE, e.getMessage());
        }
    }

    private Shell getShell(Shell activeShell) {
        Shell shell = new Shell();
        Rectangle activeShellSize = activeShell.getBounds();
        shell.setLocation((activeShellSize.width - shell.getBounds().width) / 2, (activeShellSize.height - shell.getBounds().height) / 2);
        return shell;
    }

    @CanExecute
    private boolean canExecute() {
        return ProjectController.getInstance().getCurrentProject() != null;
    }

    public static ITreeEntity getParentTreeEntity(Object[] selectedObjects) throws Exception {
        for (Object object : selectedObjects) {
            if (object instanceof ITreeEntity) {
                if (((ITreeEntity) object).getObject() instanceof FolderEntity) {
                    FolderEntity folder = (FolderEntity) ((ITreeEntity) object).getObject();
                    if (folder.getFolderType() == FolderType.WEBELEMENT) {
                        return (ITreeEntity) object;
                    }
                } else if (((ITreeEntity) object).getObject() instanceof WebElementEntity) {
                    return (ITreeEntity) ((ITreeEntity) object).getParent();
                }
            }
        }
        return null;
    }
}
