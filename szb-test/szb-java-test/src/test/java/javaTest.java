import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.DigestUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName javaTest
 * @Description TODO
 * @Author szb
 * @Date 2020/3/3 9:01
 * @Version 1.0
 **/
@RunWith(JUnit4.class)
public class javaTest {

    @Test
    public void test() {
        String monday = getDate(2);
        String sunday = getDate(1);
        System.out.println("monday------->" + monday);
        System.out.println("sunday------->" + sunday);

    }

    /**
     * 获得这周时间（周一到周日）
     *
     * @return
     */
    public static String getDate(int date)  {

        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = new Date();
        try {
            nowDate = dateFormat2.parse("2020-03-08");
        } catch (Exception e) {

        }
        Calendar c = Calendar.getInstance();
        c.setTime(nowDate);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            if (date == 1) {
                int n = 0;
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, n * 7);
//                cal.setTime(nowDate);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                System.out.println("---------" + new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
                return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
            } else {
                Calendar cal = Calendar.getInstance();
//                cal.setTime(nowDate);
                cal.add(Calendar.DATE, -7);
                cal.set(Calendar.DAY_OF_WEEK, date);
                System.out.println("---------" + new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
                return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
            }
        } else {
            if (date == 1) {
                int n = 1;
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, n * 7);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
            } else {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 0);
                cal.set(Calendar.DAY_OF_WEEK, date);
                return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
            }
        }
    }



    @Test
    public void password() {
        String password = "123456";
        System.out.println("加密密码--------》" + DigestUtils.md5DigestAsHex(password.getBytes()));
    }
}
