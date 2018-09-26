package com.tpo.fitme.gui.view.grid;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;

/**
 * @author Tiberiu
 * @since 16.07.18
 */
public abstract class AbstractGridView extends VerticalLayout implements View {

    public AbstractGridView() {
        initUI();
    }

    private void initUI() {
        setMargin(false);
        setSpacing(false);
        setHeight(100.0f, PERCENTAGE);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
