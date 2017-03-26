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

import java.io.Serializable;

/**
 * Represent a simple Hijri Calendar date.
 *
 * @author fikr4n
 *
 */
public class HijriDate implements Serializable, Comparable<HijriDate> {

    private static final long serialVersionUID = 1L;

    public final int year;
    /** Month [0..11], starting from 0, follows Java convention. */
    public final int month;
    /** Day of month [1..29] or [1..30]. */
    public final int day;
    /** Number of days in the month. */
    public final int monthLength;

    /**
     * Create new instance, there is no validation of the values.
     *
     * @param year
     * @param month
     * @param day
     * @param monthLength
     */
    public HijriDate(int year, int month, int day, int monthLength) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.monthLength = monthLength;
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    @Override
    public int compareTo(HijriDate o) {
        int c = Integer.compare(year, o.year);
        if (c != 0) return c;
        c = Integer.compare(month, o.month);
        if (c != 0) return c;
        return Integer.compare(day, o.day);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HijriDate && compareTo((HijriDate) obj) == 0;
    }

    @Override
    public int hashCode() {
        int h = year;
        h = 31 * h + month;
        h = 31 * h + day;
        return h;
    }
}
