package com.leegilbert.ltk.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

/**
 * Talk Date util, with no Holiday calendar.
 */
public final class TalkDateStreamUtil {

    /**
     * Returns an Infinite Stream of First Tuesday of Even Months, with no observed Holiday calendar.
     * Usage:    TalkDateStreamUtil.newTalkDateStream(LocalDate.now())
     *                 .limit(6)
     *                 .forEach(System.out::println);
     * @param start LocalDate  from which to stream
     * @return Stream<LocalDate> Of talk days
     */
    public static Stream<LocalDate> newTalkDateStream(LocalDate start) {
        LocalDate nextTalkDate = calcNextTalkDate(start);
        final AtomicLong vEpoch = new AtomicLong(nextTalkDate.toEpochDay());
        Stream<LocalDate> localDateStream = Stream.generate(() -> vEpoch.getAndSet(epochAdd2months.apply(vEpoch.get())))
                .map(x -> calcNextTalkDate(x));
        return localDateStream;
    }

    public static LocalDate nextTalkDate() {
        return TalkDateStreamUtil.newTalkDateStream(LocalDate.now()).limit(1).findFirst().get();
    }

    public static LocalDate forwardTalkDate(int nextn) {
        return TalkDateStreamUtil.newTalkDateStream(LocalDate.now()).limit(nextn).reduce((first, second) -> second).get();
    }

    private static final Function<Long, Long> epochAdd2months = epoch -> LocalDate.ofEpochDay(epoch).plusMonths(2).withDayOfMonth(1).toEpochDay();

    private static LocalDate calcNextTalkDate(Long startFromEpoch) {
        return calcNextTalkDate(LocalDate.ofEpochDay(startFromEpoch));
    }

    private static  LocalDate calcNextTalkDate(LocalDate startFrom) {
        LocalDate startDT = startFrom;
        if (!isEvenMonth(startDT))  {
            startDT = startDT.plusMonths(1);
        }
        LocalDate firstTuesdayInMonth = calcFirstDayInMonth(startDT, DayOfWeek.TUESDAY);
        if (!firstTuesdayInMonth.isEqual(startFrom) && !firstTuesdayInMonth.isAfter(startFrom)) {
            startDT = startDT.plusMonths(1);
        }
        firstTuesdayInMonth = calcFirstDayInMonth(startDT, DayOfWeek.TUESDAY);
        return firstTuesdayInMonth;
    }

    private static  boolean isEvenMonth(LocalDate today) {
        return today.getMonthValue() % 2 == 0;
    }


    private  static LocalDate calcFirstDayInMonth(LocalDate dt, DayOfWeek dayOfWeek) {
        return dt.with(firstInMonth(dayOfWeek)).atStartOfDay().toLocalDate();
    }


}
