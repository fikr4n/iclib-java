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

public class AngleRule {

    /** Muslim World League standard. */
    public static final AngleRule MWL = new AngleRule(18, 17);
    /** Islamic Society of North America standard. */
    public static final AngleRule ISNA = new AngleRule(15, 15);
    /** Egyptian General Authority of Survey standard. */
    public static final AngleRule EGYPT = new AngleRule(19.5, 17.5);
    /** University of Islamic Sciences (Karachi) standard. */
    public static final AngleRule KARACHI = new AngleRule(18, 18);
    /** Muhammadiyah organization (Indonesia) standard. */
    public static final AngleRule MUHAMMADIYAH = new AngleRule(20, 18);

    public final double fajr, isha;

    public AngleRule(double fajr, double isha) {
        this.fajr = fajr;
        this.isha = isha;
    }

}
