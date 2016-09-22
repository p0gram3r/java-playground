package problem;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class P019_CountingSundays implements Problem {

    @Override
    public Object getSolution() {
        int counter = 0;

        Calendar currentDate = new GregorianCalendar(1901, Calendar.JANUARY, 1);
        Calendar endDate = new GregorianCalendar(2000, Calendar.DECEMBER, 31);

        while (currentDate.before(endDate)) {
            if (currentDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                counter++;
            }
            currentDate.add(Calendar.MONTH, 1);
        }

        return counter;
    }
}
