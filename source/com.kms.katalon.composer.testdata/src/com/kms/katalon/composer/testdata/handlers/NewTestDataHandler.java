package com.kms.katalon.composer.testdata.handlers;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.kms.katalon.composer.components.impl.tree.FolderTreeEntity;
import com.kms.katalon.composer.components.impl.tree.TestDataTreeEntity;
import com.kms.katalon.composer.components.log.LoggerSingleton;
import com.kms.katalon.composer.components.tree.ITreeEntity;
import com.kms.katalon.composer.testdata.constants.StringConstants;
import com.kms.katalon.composer.testdata.views.NewTestDataDialog;
import com.kms.katalon.constants.EventConstants;
import com.kms.katalon.constants.IdConstants;
import com.kms.katalon.controller.ProjectController;
import com.kms.katalon.controller.TestDataController;
import com.kms.katalon.entity.dal.exception.FilePathTooLongException;
import com.kms.katalon.entity.folder.FolderEntity;
import com.kms.katalon.entity.folder.FolderEntity.FolderType;
import com.kms.katalon.entity.testdata.DataFileEntity;
import com.kms.katalon.tracking.service.Trackings;

public class NewTestDataHandler {

	@Inject
	IEventBroker eventBroker;

	@Inject
	EModelService modelService;

	@Inject
	MApplication application;

	@Inject
	EPartService partService;

	@Inject
	private ESelectionService selectionService;

	@Inject
	IEclipseContext context;

	private FolderTreeEntity testDataTreeRoot;

	@CanExecute
    private boolean canExecute() {
        return ProjectController.getInstance().getCurrentProject() != null;
	}

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell parentShell) {
        try {
            Object[] selectedObjects = (Object[]) selectionService.getSelection(IdConstants.EXPLORER_PART_ID);

            ITreeEntity parentTreeEntity = findParentTreeEntity(selectedObjects);
            if (parentTreeEntity == null) {
                if (testDataTreeRoot == null) {
                    return;
                }
                parentTreeEntity = testDataTreeRoot;
            }

            if (parentTreeEntity == null) {
                return;
            }

            FolderEntity parentFolderEntity = (FolderEntity) parentTreeEntity.getObject();
            TestDataController tdController = TestDataController.getInstance();
            String suggestedName = tdController.getAvailableTestDataName(parentFolderEntity,
                    StringConstants.HAND_NEW_TEST_DATA);
            NewTestDataDialog dialog = new NewTestDataDialog(parentShell, parentFolderEntity, suggestedName);
            if (dialog.open() != Dialog.OK) {
                return;
            }

            // save new test data
            DataFileEntity dataFile = tdController.saveNewTestData(dialog.getEntity());

            if (dataFile == null) {
                MessageDialog.openError(parentShell, StringConstants.ERROR_TITLE,
                        StringConstants.HAND_ERROR_MSG_UNABLE_TO_CREATE_TEST_DATA);
                return;
            }
            
            Trackings.trackCreatingObject("testData");

            eventBroker.post(EventConstants.EXPLORER_REFRESH_TREE_ENTITY, parentTreeEntity);
            eventBroker.post(EventConstants.EXPLORER_SET_SELECTED_ITEM, new TestDataTreeEntity(dataFile,
                    parentTreeEntity));
            eventBroker.post(EventConstants.TEST_DATA_OPEN, dataFile);
        } catch (FilePathTooLongException e) {
            MessageDialog.openError(parentShell, StringConstants.ERROR_TITLE, e.getMessage());
        } catch (Exception e) {
            MessageDialog.openError(parentShell, StringConstants.ERROR_TITLE,
                    StringConstants.HAND_ERROR_MSG_UNABLE_TO_CREATE_TEST_DATA);
        }
    }

	public static ITreeEntity findParentTreeEntity(Object[] selectedObjects) throws Exception {
		if (selectedObjects != null) {
			for (Object entity : selectedObjects) {
				if (entity instanceof ITreeEntity) {
					Object entityObject = ((ITreeEntity) entity).getObject();
					if (entityObject instanceof FolderEntity) {
						FolderEntity folder = (FolderEntity) entityObject;
						if (folder.getFolderType() == FolderType.DATAFILE) {
							return (ITreeEntity) entity;
						}
					} else if (entityObject instanceof DataFileEntity) {
						return ((ITreeEntity) entity).getParent();
					}
				}
			}
		}
		return null;
	}

	@Inject
	@Optional
	private void catchTestDataFolderTreeEntitiesRoot(
			@UIEventTopic(EventConstants.EXPLORER_RELOAD_INPUT) List<Object> treeEntities) {
		try {
			for (Object o : treeEntities) {
				Object entityObject = ((ITreeEntity) o).getObject();
				if (entityObject instanceof FolderEntity) {
					FolderEntity folder = (FolderEntity) entityObject;
					if (folder.getFolderType() == FolderType.DATAFILE) {
						testDataTreeRoot = (FolderTreeEntity) o;
						return;
					}
				}
			}
		} catch (Exception e) {
			LoggerSingleton.logError(e);
		}
	}

    @Inject
    @Optional
    private void execute(@UIEventTopic(EventConstants.TEST_DATA_NEW) Object eventData) {
        if (!canExecute()) {
            return;
        }
        execute(Display.getCurrent().getActiveShell());
    }

}
