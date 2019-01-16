package com.kms.katalon.composer.handlers;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

import com.kms.katalon.composer.components.impl.dialogs.MultiStatusErrorDialog;
import com.kms.katalon.composer.components.log.LoggerSingleton;
import com.kms.katalon.plugin.models.KStoreUsernamePasswordCredentials;
import com.kms.katalon.plugin.service.KStoreRestClient;

public class SearchPluginsHandler extends RequireAuthorizationHandler {

    @CanExecute
    public boolean canExecute() {
        return true;
    }
    
    @Execute
    public void execute() {
        try {
            KStoreUsernamePasswordCredentials credentials = getUsernamePasswordCredentials();
            KStoreRestClient client = new KStoreRestClient(credentials);
            client.goToSearchPluginPage();
        } catch (Exception e) {
            LoggerSingleton.logError(e);
            MultiStatusErrorDialog.showErrorDialog(e, "Error", "Cannot open Search Plugins page");
        }
    }
}
