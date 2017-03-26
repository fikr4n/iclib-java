import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import net.alqs.iclib.hijri.HijriDate;
import net.alqs.iclib.hijri.UmmQura;


@SuppressWarnings("unused")
public class HijriExample extends Example {

    public void ummqura_gregorian() {
        print(UmmQura.toGregorian(1436, 0, 1));
    }

    public void ummqura_gregorian_first() {
        print(UmmQura.toGregorian(1420, 0, 1));
    }

    public void ummqura_gregorian_last() {
        print(UmmQura.toGregorian(1450, 11, 30));
    }

    public void gregorian_ummqura() {
        print(UmmQura.fromGregorian(new GregorianCalendar(2015, 0, 1)));
    }

    public void gregorian_ummqura_first() {
        print(UmmQura.fromGregorian(new GregorianCalendar(1999, 3, 17)));
    }

    public void gregorian_ummqura_last() {
        print(UmmQura.fromGregorian(new GregorianCalendar(2029, 4, 14)));
    }

    public static void main(String[] args) throws Exception {
        new HijriExample().run();
    }

    private void print(HijriDate date) {
        super.print(String.format("%d-%d-%d (month length: %d days)", date.year, date.month + 1,
                date.day, date.monthLength));
    }

    private void print(GregorianCalendar date) {
        super.print(SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG).format(date.getTime()));
    }
}
