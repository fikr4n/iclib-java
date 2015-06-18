/**
 * Copyright (C) 2015 Fikrul Arif
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.alqs.iclib.salat;

import java.util.GregorianCalendar;

import net.alqs.iclib.Formula;

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
	private TimeAdjustment adjustments;
	private double latitude;
	private double longitude;
	private double height;
	private double timezone;
	private double julianDay;

	/**
	 * Like {@code method(angle, false, new TimeAdjustment().setZuhr(2.0/60))}.
	 * 
	 * @param angle
	 * @return
	 */
	public TimeCalculator method(AngleRule angle) {
		return method(angle, false, new TimeAdjustment().zuhr(2.0/60));
	}
	
	/**
	 * Set method and adjustment of calculation.
	 * 
	 * @param angle Fajr and Isha angle
	 * @param hanafiAsrRatio ratio of object's shadow to determine Asr time, whether Hanafi or majority
	 * @param adjustments result adjustment
	 * @return self for chaining
	 */
	public TimeCalculator method(AngleRule angle, boolean hanafiAsrRatio, TimeAdjustment adjustments) {
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
		// julian day of local midday (minus timezone, plus 12 hours)
		double jd = Formula.adjustJdHour(this.julianDay, -this.timezone + 12);
		double ds = Formula.declSun(jd);
		double transit = Formula.zuhr(this.longitude, this.timezone, Formula.eqTime(jd));
		double lat = this.latitude;
		return new Times((long) (jd - JAVA_DATE_EPOCH_JD) * 24 * 60 * 60 * 1000,
			Formula.fajr   (transit, lat, ds, this.angle.fajr) + adjustments.getFajr(),
			Formula.sunrise(transit, lat, ds, this.height)     + adjustments.getSunrise(),
			transit                                            + adjustments.getZuhr(),
			Formula.asr    (transit, lat, ds, this.asrRatio)   + adjustments.getAsr(),
			Formula.maghrib(transit, lat, ds, this.height)     + adjustments.getMaghrib(),
			Formula.isha   (transit, lat, ds, this.angle.isha) + adjustments.getIsha());
	}

}
