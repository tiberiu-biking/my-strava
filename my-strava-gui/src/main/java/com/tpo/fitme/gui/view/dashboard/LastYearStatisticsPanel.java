package com.tpo.fitme.gui.view.dashboard;

import com.tpo.fitme.service.statistics.StatisticsService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * @author Tiberiu
 * @since 13.06.18
 */
@UIScope
@SpringComponent
public class LastYearStatisticsPanel extends YearlyStatisticsPanel {

    @Autowired
    public LastYearStatisticsPanel(StatisticsService statisticsService) {
        super(statisticsService);
    }

    @Override
    LocalDateTime getYear() {
        return LocalDateTime.now().minusYears(1).with(TemporalAdjusters.firstDayOfYear());
    }

    @Override
    String getTitle() {
        return "Last Year";
    }

}
