package com.tpo.fitme.gui.component.textfield;

import com.tpo.fitme.gui.constants.ThemedIcon;
import com.vaadin.ui.TextField;

/**
 * @author Tiberiu
 * @since 14.06.18
 */
public class ReadOnlyTextField extends TextField {

    public ReadOnlyTextField(String caption, String value, ThemedIcon themedIcon) {
        super(caption, value);
        setReadOnly(true);
        setIcon(themedIcon.getResource());
    }
}
