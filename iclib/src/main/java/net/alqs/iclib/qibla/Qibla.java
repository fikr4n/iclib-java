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
package net.alqs.iclib.qibla;

import net.alqs.iclib.Formula;

/**
 * Qibla direction calculator.
 *
 * @author fikr4n
 *
 */
public class Qibla {

    /**
     * Return qibla direction in degrees from the north (clock-wise).
     *
     * @param lat latitude in degrees
     * @param lng longitude in degrees
     * @return 0 means north, 90 means east, 270 means west, etc
     */
    public static double findDirection(double lat, double lng) {
        return Formula.qibla(lat, lng);
    }

    /**
     * Calculate qibla direction as 3-tuple of degree-minute-second.
     *
     * @param lat
     * @param lng
     * @return
     */
    public static Dms findDirectionDms(double lat, double lng) {
        return new Dms(Formula.qibla(lat, lng));
    }

}
