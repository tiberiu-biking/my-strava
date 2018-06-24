package com.tpo.fitme.gui.view.dashboard;

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
    public ThisYearStatisticsPanel(StatisticsService statisticsService) {
        super(statisticsService);
    }

    @Override
    int getYear() {
        return LocalDateTime.now().getYear();
        //return LocalDateTime.now().minusYears(1).with(TemporalAdjusters.firstDayOfYear());
    }

    @Override
    String getTitle() {
        return "This Year";
    }

}
