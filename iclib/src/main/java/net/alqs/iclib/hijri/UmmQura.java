/**
 * Copyright (C) 2015 Fikrul Arif
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.alqs.iclib.hijri;

import net.alqs.iclib.Formula;

import java.util.GregorianCalendar;

/**
 * Umm al-Qura Hijri Calendar converter.
 *
 * @author fikr4n
 *
 */
public class UmmQura {

    /**
     * Convert from Gregorian date.
     *
     * <p>This is valid only for 1420-1450 AH (April 17 1999 - May 14 2029).
     *
     * @param cal
     * @return
     */
    public static HijriDate fromGregorian(GregorianCalendar cal) {
        // valid from 1420 AH (1999-04-17 CE) to 1450 AH (2029-05-14 CE)
        int jd = (int) (Formula.gregorianToJd(cal.get(GregorianCalendar.YEAR),
                cal.get(GregorianCalendar.MONTH) + 1,
                cal.get(GregorianCalendar.DAY_OF_MONTH)) + 0.5); // jd midday
        int accu = 2451286; // jd of 1999-04-17 midday
        int i = -1;
        while (accu <= jd) {
            i += 1;
            accu += monthLen[i] + 29;
        }
        int monthStart = accu - monthLen[i] - 29;
        // here i is index where the month we're looking for is
        return new HijriDate(i / 12 + 1420, i % 12, jd - monthStart + 1, monthLen[i] + 29);
    }

    /**
     * Convert to Gregorian date.
     *
     * <p>This is valid only for 1420-1450 AH (April 17 1999 - May 14 2029).
     *
     * @param y year
     * @param m month [0..11]
     * @param d day of month [1..30]
     * @return
     */
    public static GregorianCalendar toGregorian(int y, int m, int d) {
        int index = (y - 1420) * 12 + m;
        int sum = 0;
        for (int i = 0; i < index; ++i)
            sum += monthLen[i] + 29;
        double jd = 2451285.5 + sum + d - 1;
        int[] greg = Formula.jdToGregorian(jd);
        return new GregorianCalendar(greg[0], greg[1] - 1, greg[2]);
    }

    private static final byte[] monthLen = {
            0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1,
            0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1,
            1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1,
            1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1,
            1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0,
            1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0,
            0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1,
            0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0,
            1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1,
            0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1,
            0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1,
            0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1,
            0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0,
            1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0,
            0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1,
            0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
            1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1,
            1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0,
            1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1,
            0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0,
            1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0,
            0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1,
            0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1,
            0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1,
            0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0,
            1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0,
            0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1,
            0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0,
            1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1};

}
