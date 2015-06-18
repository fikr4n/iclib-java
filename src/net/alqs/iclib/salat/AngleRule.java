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

public class AngleRule {
	
	public static final AngleRule MWL = new AngleRule(18, 17);
	public static final AngleRule ISNA = new AngleRule(15, 15);
	public static final AngleRule EGYPT = new AngleRule(19.5, 17.5);
	public static final AngleRule KARACHI = new AngleRule(18, 18);
	public static final AngleRule MUHAMMADIYAH = new AngleRule(20, 18);

	public final double fajr, isha;

	public AngleRule(double fajr, double isha) {
		this.fajr = fajr;
		this.isha = isha;
	}
	
}
