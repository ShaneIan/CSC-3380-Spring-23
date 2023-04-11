package ScheduleAnalyzer;

import ScheduleObjectBuilder.Schedule;

public interface ScheduleAnalyzer {

    public void rankScheduleOptions();

    public Schedule getHighestRankedScheduleOption();

    public Schedule getLowestRankedScheduleOption();
}
