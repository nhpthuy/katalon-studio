package com.kms.katalon.composer.project.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.osgi.service.event.Event;

import com.kms.katalon.composer.components.event.EventBrokerSingleton;
import com.kms.katalon.composer.components.impl.event.EventServiceAdapter;
import com.kms.katalon.composer.components.impl.providers.TypeCheckedStyleCellLabelProvider;
import com.kms.katalon.composer.components.log.LoggerSingleton;
import com.kms.katalon.composer.project.constants.StringConstants;
import com.kms.katalon.composer.project.exception.MissingProjectSettingPageException;
import com.kms.katalon.constants.EventConstants;
import com.kms.katalon.controller.ProjectController;
import com.kms.katalon.execution.launcher.manager.LauncherManager;
import com.kms.katalon.preferences.internal.PreferencesRegistry;

public class SettingHandler {

    @Inject
    private IEclipseContext eclipseContext;

    @CanExecute
    public boolean canExecute() {
        return (ProjectController.getInstance().getCurrentProject() != null
                && !LauncherManager.getInstance().isAnyLauncherRunning());
    }

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell, PreferencesRegistry preferencesRegistry) {
        PreferenceManager pm = preferencesRegistry.getPreferenceManager(PreferencesRegistry.PREFS_PROJECT_XP);

        hideIOSPageOnNoneMacOS(pm);

        PreferenceDialog dialog = new PreferenceDialog(shell, pm) {
            @Override
            protected TreeViewer createTreeViewer(Composite parent) {
                TreeViewer treeViewer = super.createTreeViewer(parent);
                treeViewer.setLabelProvider(new PreferenceLabelProvider());
                return treeViewer;
            }

            @Override
            protected void addListeners(TreeViewer viewer) {
                super.addListeners(viewer);
                registerPageChangeListener();
            }

            private void registerPageChangeListener() {
                EventBrokerSingleton.getInstance().getEventBroker().subscribe(EventConstants.SETTINGS_PAGE_CHANGE,
                        new EventServiceAdapter() {

                            @Override
                            public void handleEvent(Event event) {
                                String pageId = (String) getObject(event);
                                getTreeViewer().setSelection(new StructuredSelection(findNodeMatching(pageId)));
                            }
                        });
            }

            @Override
            protected Point getInitialSize() {
                return new Point(900, 600);
            }
        };
        dialog.setSelectedNode(StringConstants.PROJECT_INFORMATION_SETTINGS_PAGE_ID);
        dialog.create();
        dialog.getTreeViewer().setComparator(new ViewerComparator() {
            @Override
            public int compare(Viewer viewer, Object e1, Object e2) {
                if (e1 instanceof PreferenceNode && e2 instanceof PreferenceNode) {
                    String pageId1 = ((PreferenceNode) e1).getId();
                    String pageId2 = ((PreferenceNode) e2).getId();
                    if (StringConstants.PROJECT_INFORMATION_SETTINGS_PAGE_ID.equals(pageId1)) {
                        return -1;
                    }
                    return pageId1.compareToIgnoreCase(pageId2);
                }
                return super.compare(viewer, e1, e2);
            }
        });
        dialog.getTreeViewer().expandToLevel(2);
        dialog.getShell().setText(StringConstants.HAND_PROJ_SETTING);
        dialog.open();
    }

    @Inject
    @Optional
    public void openSettings(@UIEventTopic(EventConstants.PROJECT_SETTINGS) Object eventData) {
        if (!canExecute()) {
            return;
        }

        PreferencesRegistry preferencesRegistry = ContextInjectionFactory.make(PreferencesRegistry.class,
                eclipseContext);

        execute(Display.getCurrent().getActiveShell(), preferencesRegistry);
    }

    private void hideIOSPageOnNoneMacOS(PreferenceManager pm) {
        if (Platform.OS_MACOSX.equals(Platform.getOS())) {
            return;
        }

        try {
            IPreferenceNode executionSettings = null;
            for (IPreferenceNode node : pm.getRootSubNodes()) {
                if (StringConstants.PROJECT_EXECUTION_SETTINGS_PAGE_ID.equals(node.getId())) {
                    executionSettings = node;
                    break;
                }
            }
            if (executionSettings == null) {
                throw new MissingProjectSettingPageException(StringConstants.PROJECT_EXECUTION_SETTINGS_PAGE_ID);
            }

            IPreferenceNode defaultExecutionSettings = executionSettings
                    .findSubNode(StringConstants.PROJECT_EXECUTION_SETTINGS_DEFAULT_PAGE_ID);
            if (defaultExecutionSettings == null) {
                throw new MissingProjectSettingPageException(
                        StringConstants.PROJECT_EXECUTION_SETTINGS_DEFAULT_PAGE_ID);
            }

            IPreferenceNode mobileNode = defaultExecutionSettings
                    .findSubNode(StringConstants.PROJECT_EXECUTION_SETTINGS_DEFAULT_MOBILE_PAGE_ID);
            if (mobileNode == null) {
                throw new MissingProjectSettingPageException(
                        StringConstants.PROJECT_EXECUTION_SETTINGS_DEFAULT_MOBILE_PAGE_ID);
            }

            IPreferenceNode iOSNode = mobileNode
                    .remove(StringConstants.PROJECT_EXECUTION_SETTINGS_DEFAULT_MOBILE_IOS_PAGE_ID);
            if (iOSNode == null) {
                throw new MissingProjectSettingPageException(
                        StringConstants.PROJECT_EXECUTION_SETTINGS_DEFAULT_MOBILE_IOS_PAGE_ID);
            }
        } catch (MissingProjectSettingPageException e) {
            // In case of someone changes the page ID in e4xmi, this will get DEV attention
            LoggerSingleton.logError(e);
        }
    }

    private final class PreferenceLabelProvider extends TypeCheckedStyleCellLabelProvider<PreferenceNode> {
        private PreferenceLabelProvider() {
            super(0);
        }

        @Override
        protected Class<PreferenceNode> getElementType() {
            return PreferenceNode.class;
        }

        @Override
        protected Image getImage(PreferenceNode element) {
            return null;
        }

        @Override
        protected String getText(PreferenceNode element) {
            return element.getLabelText();
        }
    }
}
