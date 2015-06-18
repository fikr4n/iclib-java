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

public class TimeAdjustment {

	private double fajr, sunrise, zuhr, asr, maghrib, isha;

	/**
	 * Create new instance with all adjustments set to 0.0.
	 */
	public TimeAdjustment() {
	}
	
	public TimeAdjustment(double fajr, double sunrise, double zuhr, double asr,
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
