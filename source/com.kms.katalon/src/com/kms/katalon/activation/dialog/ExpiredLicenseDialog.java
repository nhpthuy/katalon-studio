package com.kms.katalon.activation.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.kms.katalon.composer.components.impl.dialogs.AbstractDialog;

public class ExpiredLicenseDialog extends AbstractDialog {

    public ExpiredLicenseDialog(Shell parentShell) {
        super(parentShell, false);
    }

    @Override
    protected void registerControlModifyListeners() {
    }

    @Override
    protected void setInput() {
    }

    @Override
    protected Control createDialogContainer(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout());

        Label lblText = new Label(composite, SWT.NONE);
        lblText.setText("Your license expired. Katalon Studio will automatically close in 30 seconds. Please save your work");
        return composite;
    }
    
    @Override
    protected int getShellStyle() {
        return SWT.DIALOG_TRIM;
    }
    
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, "Close now", true);
    }
}
