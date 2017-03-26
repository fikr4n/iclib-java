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

/**
 * Time adjustment of prayer times.
 *
 * <p>Adjustment of calculation result has some advantages, e.g.:
 * <ul>
 * <li>To wait for the Sun to slightly down to the west since prayer when the Sun is exactly in the
 * center/topmost position is prohibited.
 * <li>To cover western area of a city.
 * <li>To cover/avoid calculation error.
 * </ul>
 *
 * @author fikr4n
 *
 */
public class TimeAdjustment extends BaseTimeAdjustment {

    /**
     * Create new instance with all adjustments set to 0.0.
     */
    public TimeAdjustment() {
        super(0, 0, 0, 0, 0, 0);
    }

    /**
     * Create new instance, all arguments are in minutes.
     */
    public TimeAdjustment(double fajr, double sunrise, double zuhr, double asr,
                          double maghrib, double isha) {
        super(fajr, sunrise, zuhr, asr, maghrib, isha);
    }

    public TimeAdjustment fajr(double fajr) {
        this.fajr = fajr;
        return this;
    }

    public TimeAdjustment sunrise(double sunrise) {
        this.sunrise = sunrise;
        return this;
    }

    public TimeAdjustment zuhr(double zuhr) {
        this.zuhr = zuhr;
        return this;
    }

    public TimeAdjustment asr(double asr) {
        this.asr = asr;
        return this;
    }

    public TimeAdjustment maghrib(double maghrib) {
        this.maghrib = maghrib;
        return this;
    }

    public TimeAdjustment isha(double isha) {
        this.isha = isha;
        return this;
    }
}

class BaseTimeAdjustment {

    /** Each time is zero. */
    public static final BaseTimeAdjustment ZEROS = new BaseTimeAdjustment(0, 0, 0, 0, 0, 0);
    /** Each time is 2 minutes. */
    public static final BaseTimeAdjustment TWO_MINUTES = new BaseTimeAdjustment(2, 2, 2, 2, 2, 2);
    /** Each time is zero, except Zuhr, it is two minutes. */
    public static final BaseTimeAdjustment TWO_MINUTES_ZUHR = new BaseTimeAdjustment(0, 0, 2, 0, 0, 0);

    protected double fajr, sunrise, zuhr, asr, maghrib, isha;

    public BaseTimeAdjustment(double fajr, double sunrise, double zuhr, double asr,
                              double maghrib, double isha) {
        this.fajr = fajr;
        this.sunrise = sunrise;
        this.zuhr = zuhr;
        this.asr = asr;
        this.maghrib = maghrib;
        this.isha = isha;
    }

    public double getFajr() {
        return fajr;
    }

    public double getSunrise() {
        return sunrise;
    }

    public double getZuhr() {
        return zuhr;
    }

    public double getAsr() {
        return asr;
    }

    public double getMaghrib() {
        return maghrib;
    }

    public double getIsha() {
        return isha;
    }
}
