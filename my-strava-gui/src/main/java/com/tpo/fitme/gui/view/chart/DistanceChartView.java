package com.tpo.fitme.gui.view.chart;

import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.StringIntData;
import com.tpo.fitme.domain.summary.ActivitiesSummary;
import com.tpo.fitme.domain.summary.Summary;
import com.tpo.fitme.gui.component.chart.StravaChart;
import com.tpo.fitme.gui.domain.UserSession;
import com.tpo.fitme.service.summary.ActivitiesSummaryService;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static com.tpo.fitme.service.constants.Constants.DEFAULT_CHART_PERIOD;

@SpringView(name = DistanceChartView.VIEW_NAME)
public class DistanceChartView extends AbstractActivitiesSummaryView {

    public static final String VIEW_NAME = "distances";

    @Autowired
    public DistanceChartView(UserSession userSession, ActivitiesSummaryService activitiesSummaryService) {
        super(userSession, activitiesSummaryService);
    }

    @PostConstruct
    public void init() {
        addComponent(buildChart(activitiesSummaryService.generateSummarySince(athleteId, DEFAULT_CHART_PERIOD)));
    }

    private StravaChart buildChart(Summary summary) {
        return new StravaChart("Distances", summary.getActivitiesSummaries()) {

            @Override
            protected HighChartsData getColumnValue(ActivitiesSummary activity) {
                return new StringIntData(activity.getMonth() + "/" + activity.getYear(), activity.getDistance().intValue());
            }
        };
    }

}