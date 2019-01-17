package com.leeGilbert.ltk.util;

import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TalkDateStreamUtilTest {

    @Test
    public void TalkDateStreamContainsTuesdays() { //TODO add more asserts
        LocalDate today = LocalDate.now();
        TalkDateStreamUtil.newTalkDateStream(today)
                .limit(6)
                .forEach(d -> {
                    Assert.assertThat(d.isBefore(today), is(false)) ;
                    Assert.assertThat(d.getDayOfWeek(), is(DayOfWeek.TUESDAY)) ;
                });
    }
}