package com.tpo.fitme.gui.component.textfield;

import com.tpo.fitme.gui.constants.SportIcon;
import com.vaadin.ui.TextField;

/**
 * @author Tiberiu
 * @since 14.06.18
 */
public class ReadOnlyTextField extends TextField {

    public ReadOnlyTextField(String caption, String value, SportIcon sportIcon) {
        super(caption, value);
        setReadOnly(true);
        setIcon(sportIcon.getResource());
    }
}
