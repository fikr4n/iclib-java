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

import net.alqs.iclib.Formula;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Prayer times calculator.
 *
 * @author fikr4n
 *
 */
public class TimeCalculator {

    /** Julian Day of 1970-01-01 midday. */
    private static final double JAVA_DATE_EPOCH_JD = 2440588;

    private AngleRule angle;
    private int asrRatio;
    private BaseTimeAdjustment adjustments;
    private double latitude;
    private double longitude;
    private double height;
    private Double timezone;
    private Double julianDay;

    /**
     * Like calling {@code method(angle, false, TimeAdjustment.TWO_MINUTES_ZUHR)}.
     *
     * @param angle
     * @return
     */
    public TimeCalculator method(AngleRule angle) {
        return method(angle, false, TimeAdjustment.TWO_MINUTES_ZUHR);
    }

    /**
     * Set method and adjustment of calculation.
     *
     * @param angle Fajr and Isha angle
     * @param hanafiAsrRatio ratio of object's shadow to determine Asr time, whether Hanafi or majority
     * @param adjustments result adjustment
     * @return self for chaining
     */
    public TimeCalculator method(AngleRule angle, boolean hanafiAsrRatio, BaseTimeAdjustment adjustments) {
        this.angle = angle;
        this.asrRatio = hanafiAsrRatio ? Formula.ASR_RATIO_HANAFI : Formula.ASR_RATIO_MAJORITY;
        this.adjustments = adjustments;
        return this;
    }

    /**
     * Set the location.
     *
     * @param lat latitude in degrees
     * @param lng longitude in degrees
     * @param h altitude/height of the place in meters
     * @param timezone timezone in hours, x means UTC+x
     * @return self for chaining
     */
    public TimeCalculator location(double lat, double lng, double h, double timezone) {
        this.latitude = lat;
        this.longitude = lng;
        this.height = h;
        this.timezone = timezone;
        return this;
    }

    /**
     * Set the date.
     *
     * @param date
     * @return self for chaining
     */
    public TimeCalculator date(GregorianCalendar date) {
        this.julianDay = Formula.gregorianToJd(date.get(GregorianCalendar.YEAR),
                date.get(GregorianCalendar.MONTH) + 1, date.get(GregorianCalendar.DAY_OF_MONTH));
        return this;
    }

    public TimeCalculator date(Date date, TimeZone zone) {
        GregorianCalendar g = new GregorianCalendar(zone);
        g.setTime(date);
        return date(g);
    }

    /**
     * Add the date by days.
     *
     * @param days
     * @return
     */
    public TimeCalculator dateRelative(int days) {
        this.julianDay += days;
        return this;
    }

    /**
     * Calculate the prayer times.
     *
     * <p>This method can be called several times. For example, you set the date
     * and call this method, update the date to tomorrow and call this method.
     */
    public Times calculate() {
        if (angle == null || julianDay == null || timezone == null)
            throw new IllegalStateException("Some calculation parameter is not initialized yet");

        // julian day of local midday (minus timezone, plus 12 hours)
        double jd = Formula.adjustJdHour(this.julianDay, -this.timezone + 12);
        double ds = Formula.declSun(jd);
        double transit = Formula.zuhr(this.longitude, this.timezone, Formula.eqTime(jd));
        double lat = this.latitude;
        return new Times((long) (jd - JAVA_DATE_EPOCH_JD) * 24 * 60 * 60 * 1000,
                Formula.fajr(transit, lat, ds, this.angle.fajr) + adjustments.getFajr() / 60.0,
                Formula.sunrise(transit, lat, ds, this.height) + adjustments.getSunrise() / 60.0,
                transit + adjustments.getZuhr() / 60.0,
                Formula.asr(transit, lat, ds, this.asrRatio) + adjustments.getAsr() / 60.0,
                Formula.maghrib(transit, lat, ds, this.height) + adjustments.getMaghrib() / 60.0,
                Formula.isha(transit, lat, ds, this.angle.isha) + adjustments.getIsha() / 60.0);
    }

}
