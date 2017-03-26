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
package net.alqs.iclib.salat;

import java.util.Date;
import java.util.Iterator;

/**
 * Result of TimeCalculator.
 *
 * @author fikr4n
 *
 */
public class Times implements Iterable<Date> {

    public static final int FAJR = 0, SUNRISE = 1, ZUHR = 2, ASR = 3, MAGHRIB = 4, ISHA = 5;
    private static final int N = 6;

    private boolean useSecond = false;
    private double[] times;
    private long millis;

    Times(long millis, double... times) {
        this.times = times;
        this.millis = millis;
        if (times[SUNRISE] == Double.NEGATIVE_INFINITY || times[MAGHRIB] == Double.POSITIVE_INFINITY) {
            times[ZUHR] = Double.POSITIVE_INFINITY;
            times[ASR] = Double.POSITIVE_INFINITY;
        }
    }

    /**
     * Return the time as {@link Date}.
     *
     * <p>{@link #isUseSecond()} is considered.
     *
     * @param timeName ZUHR, ASR, etc
     * @return
     */
    public Date getTime(int timeName) {
        double t = times[timeName];
        if (t == Double.POSITIVE_INFINITY || t == Double.NEGATIVE_INFINITY)
            return null;
        // negative hours will raise exception
        if (isUseSecond())
            return new Date(this.millis + (long) Math.ceil(t * 60 * 60) * 1000);
        else
            return new Date(this.millis + (long) Math.ceil(t * 60) * 60 * 1000);
    }

    /**
     * Return the time as hour-minute-second.
     *
     * <p>{@link #isUseSecond()} is considered.
     *
     * @param timeName ZUHR, ASR, etc
     * @return
     */
    public Hms getHms(int timeName) {
        double t = times[timeName];
        if (t == Double.POSITIVE_INFINITY || t == Double.NEGATIVE_INFINITY)
            return null;
        if (isUseSecond())
            return new Hms(t);
        else
            return new Hms(Math.ceil(t * 60) / 60);
    }

    public Date getFajr() {
        return getTime(FAJR);
    }

    public Date getSunrise() {
        return getTime(SUNRISE);
    }

    public Date getZuhr() {
        return getTime(ZUHR);
    }

    public Date getAsr() {
        return getTime(ASR);
    }

    public Date getMaghrib() {
        return getTime(MAGHRIB);
    }

    public Date getIsha() {
        return getTime(ISHA);
    }

    /**
     * Whether second is used, if not it will be zero and minute is rounded to the top (ceil).
     */
    public boolean isUseSecond() {
        return useSecond;
    }

    public void setUseSecond(boolean useSecond) {
        this.useSecond = useSecond;
    }

    @Override
    public Iterator<Date> iterator() {
        return new Iterator<Date>() {

            int nextIndex = 0;

            @Override
            public boolean hasNext() {
                return nextIndex < N;
            }

            @Override
            public Date next() {
                return getTime(nextIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}
