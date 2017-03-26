import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import net.alqs.iclib.salat.AngleRule;
import net.alqs.iclib.salat.TimeAdjustment;
import net.alqs.iclib.salat.TimeCalculator;
import net.alqs.iclib.salat.Times;


@SuppressWarnings("unused")
public class SalatExample extends Example {

    private GregorianCalendar date;

    private SalatExample() {
        date = new GregorianCalendar();
        print("Date: " + SimpleDateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG).format(date.getTime()));
        print("");
    }

    public void egypt_gettime() {
        Times t = new TimeCalculator().date(date)
                .location(-6.38043079, 106.85337984, 0, 7)
                .method(AngleRule.EGYPT)
                .calculate();
        DateFormat f = new SimpleDateFormat("HH:mm");
        print(f.format(t.getTime(Times.FAJR)));
        print(f.format(t.getTime(Times.SUNRISE)));
        print(f.format(t.getTime(Times.ZUHR)));
        print(f.format(t.getTime(Times.ASR)));
        print(f.format(t.getTime(Times.MAGHRIB)));
        print(f.format(t.getTime(Times.ISHA)));
    }

    public void egypt_property_withsec() {
        Times t = new TimeCalculator().date(date)
                .location(-6.38043079, 106.85337984, 0, 7)
                .method(AngleRule.EGYPT)
                .calculate();
        t.setUseSecond(true);
        print(t.getFajr());
        print(t.getSunrise());
        print(t.getZuhr());
        print(t.getAsr());
        print(t.getMaghrib());
        print(t.getIsha());
    }

    public void egypt_iter_noadjust() {
        Times t = new TimeCalculator().date(date)
                .location(-6.38043079, 106.85337984, 0, 7)
                .method(AngleRule.EGYPT, false, TimeAdjustment.ZEROS)
                .calculate();
        for (Date i : t)
            print(i);
    }

    public void muhammadiyah() {
        TimeCalculator c = new TimeCalculator().date(date)
                .location(-6.38043079, 106.85337984, 0, 7)
                .method(AngleRule.MUHAMMADIYAH);
        for (Date i : c.calculate())
            print(i);
    }

    public void mwl_noadjust() {
        TimeCalculator c = new TimeCalculator().date(date)
                .location(-6.38043079, 106.85337984, 0, 7)
                .method(AngleRule.MWL, false, TimeAdjustment.ZEROS);
        for (Date i : c.calculate())
            print(i);
    }

    public void mwl_hanafi_noadjust() {
        TimeCalculator c = new TimeCalculator().date(date)
                .location(-6.38043079, 106.85337984, 0, 7)
                .method(AngleRule.MWL, true, TimeAdjustment.ZEROS);
        for (Date i : c.calculate())
            print(i);
    }

    public void mwl_noadjust_100m() {
        TimeCalculator c = new TimeCalculator().date(date)
                .location(-6.38043079, 106.85337984, 100, 7)
                .method(AngleRule.MWL, false, TimeAdjustment.ZEROS);
        for (Date i : c.calculate())
            print(i);
    }

    public void mwl_1jan2015() {
        TimeCalculator c = new TimeCalculator().date(new GregorianCalendar(2015, 0, 1))
                .location(-6.38043079, 106.85337984, 0, 7)
                .method(AngleRule.MWL);
        for (Date i : c.calculate())
            print(i);
    }

    public void mwl_1jan2015_tomorrow() {
        TimeCalculator c = new TimeCalculator().date(new GregorianCalendar(2015, 0, 1))
                .location(-6.38043079, 106.85337984, 0, 7)
                .method(AngleRule.MWL);
        c.dateRelative(+1);
        for (Date i : c.calculate())
            print(i);
    }

    public static void main(String[] args) throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC+7:00")); // for demonstration, use the same timezone
        new SalatExample().run();
    }
}
