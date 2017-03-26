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

import java.io.Serializable;

/**
 * Representation of time in hour-minute-second.
 *
 * @author fikr4n
 *
 */
public class Hms implements Serializable, Comparable<Hms> {

    private static final long serialVersionUID = 1L;

    public final int hour, minute, second;

    public Hms(double hours) {
        double seconds = Math.ceil(hours * 3600);
        this.hour = (int) Math.floor(seconds / 3600);
        this.minute = (int) Math.floor(floorMod(seconds, 3600) / 60);
        this.second = (int) floorMod(seconds, 60);
    }

    private static double floorMod(double divident, double divisor) {
        double m = divident % divisor;
        return m < 0 ? m + divisor : m;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    @Override
    public int compareTo(Hms o) {
        int c = Integer.compare(hour, o.hour);
        if (c != 0) return c;
        c = Integer.compare(minute, o.minute);
        if (c != 0) return c;
        return Integer.compare(second, o.second);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Hms && compareTo((Hms) obj) == 0;
    }

    @Override
    public int hashCode() {
        int h = hour;
        h = 31 * h + minute;
        h = 31 * h + second;
        return h;
    }
}
