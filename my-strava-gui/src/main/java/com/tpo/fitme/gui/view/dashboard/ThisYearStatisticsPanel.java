package com.tpo.fitme.gui.view.dashboard;

import com.tpo.fitme.gui.domain.UserSession;
import com.tpo.fitme.service.statistics.StatisticsService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author Tiberiu
 * @since 13.06.18
 */
@UIScope
@SpringComponent
class ThisYearStatisticsPanel extends YearlyStatisticsPanel {

    @Autowired
    public ThisYearStatisticsPanel(UserSession userSession, StatisticsService statisticsService) {
        super(userSession, statisticsService);
    }

    @Override
    int getYear() {
        return LocalDateTime.now().getYear();
    }

    @Override
    String getTitle() {
        return "This Year";
    }

}
