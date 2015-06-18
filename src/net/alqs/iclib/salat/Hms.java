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

public class Hms {

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
}
