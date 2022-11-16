package ru.gb.market.core.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
@Slf4j
public class StatisticUtil {

    private final Map<Class<?>, Long> statistic = new HashMap<>();

    public void add(Class<?> serviceClass, Long time) {
        log.info("CurrentTime execution on method {}:{} ms", serviceClass.getName(), time);
        if (statistic.containsKey(serviceClass)) {
            Long summaryTime = statistic.get(serviceClass) + time;
            statistic.put(serviceClass, summaryTime);
        } else {
            statistic.put(serviceClass, time);
        }
        log.info("SummaryTime execution on method {}:{} ms", serviceClass.getName(), statistic.get(serviceClass));
    }

    public Map<Class<?>, Long> getStatistic (){
        return statistic;
    }


}
