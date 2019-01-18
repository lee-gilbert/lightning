package com.leeGilbert.ltk.util;


import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstInMonth;
import static org.hamcrest.Matchers.is;

public class TalkDateStreamUtilTest {

    public static final int MAX_SIZE = 888;
    LocalDate start = LocalDate.now();

    @Test
    public void StreamContainsExpectedTuesdays() {
        TalkDateStreamUtil.newTalkDateStream(start)
                .limit(MAX_SIZE)
                .forEach(d -> {
                    Assert.assertThat(d.isBefore(start), is(false)) ;
                    Assert.assertThat("Even Month", d.getMonthValue() % 2,  is( 0));
                    Assert.assertThat("A Tuesday", d.getDayOfWeek(), is(DayOfWeek.TUESDAY)) ;
                    Assert.assertThat("1st Tuesday in Month", d, is(d.with(firstInMonth(DayOfWeek.TUESDAY)).atStartOfDay().toLocalDate()));
                });


    }

    @Test
    public void StreamLimitCountMatchesExpectedWithNoDuplicates() {
        List<LocalDate> tuesdsyList = TalkDateStreamUtil.newTalkDateStream(start.minusMonths(MAX_SIZE))
                .limit(MAX_SIZE)
                .collect(Collectors.toList());
        Assert.assertThat("Element count matches expected", tuesdsyList.size() , is(MAX_SIZE) );

        Set<LocalDate> lSet = new HashSet<>();
        lSet.addAll(tuesdsyList);
        Assert.assertThat("No Duplicates", lSet.size(), is(tuesdsyList.size()));
    }
}