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
package net.alqs.iclib.qibla;

public class Dms {

	public final int degree, minute;
	public final double second;

	public Dms(double degree) {
		double seconds = (degree * 3600);
		this.degree = (int) (seconds / 3600);
		this.minute = (int) (seconds % 3600 / 60);
		this.second = seconds % 60;
	}
	
	public String toString(int prec) {
		int d = degree;
		int m = minute;
		double s = second;
		if (d < 0 || m < 0 || s < 0)
			return String.format("-%d° %d′ %." + prec + "f″", -d, -m, -s);
		else
			return String.format("%d° %d′ %." + prec + "f″", d, m, s);
	}
	
	@Override
	public String toString() {
		return toString(0);
	}
}
