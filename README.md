# ICLib Java
Islamic Calculation Library (ICLib) contains calculations/algorithms needed specifically by muslims and people in muslim countries, such as salat (prayer) times, qibla direction, and Hijri conversion.

This library is written in Java and is a porting of the [Python](https://github.com/fikr4n/iclib-python) version. More information about the calculation method you should choose, etc, are available in the README file there.

**Notes on Hijri conversion**

Any Hijri conversion including Umm al-Qura is not used as reference for ibadah e.g. beginning of saum (fasting), Eid al-Fitr, and Eid al-Adha.

## Usage example

Latitude and longitude are in degrees, positive values for north and east respectively, negative values for south and west respectively. More examples are available in `example` directory.

```java
	DateFormat format = new SimpleDateFormat("HH:mm");
	// optional: set timezone to make sure formatting is correct
	// format.setTimeZone(timeZone)
	
	Times times = new TimeCalculator()
		// today
		.date(new GregorianCalendar())
		// latitude, longitude, altitude, timezone
		.location(-6.38043079, 106.85337984, 0, +7)
		// fajr and isha rule [, asr rule, time adjustment]
		.method(AngleRule.EGYPT) // .method(AngleRule.EGYPT, false, TimeAdjustment.ZEROS)
		.calculate();
	for (Date time : times) // as java.util.Date
		print(format.format(time));
	
	// other styles
	print(times.getZuhr()); // as java.util.Date
	print(times.getTime(Times.ASR)); // as java.util.Date
	print(times.getHms(Times.MAGHRIB)); // simply as Hms (hour-minute-second)
```

```java
	// latitude and longitude of the user
	double lat = -6.169777778, lng = 106.8307333;
	print(Qibla.findDirection(lat, lng)); // as double (in degrees)
	print(Qibla.findDirectionDms(lat, lng)); // as Dms (degree-minute-second)
```

```java
	/* By Java convention, month is 0-based and day-of-month is 1-based */
	// year, month, day
	print(UmmQura.toGregorian(1436, 0, 1)); // as java.util.GregorianCalendar
	print(UmmQura.fromGregorian(new GregorianCalendar(2015, 0, 1))); // as HijriDate
```

_We need your dua and support_

**Salam and have fun!**

