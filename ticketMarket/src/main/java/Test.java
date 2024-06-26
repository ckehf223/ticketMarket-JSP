import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
 public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("YY.MM.dd hh:mm");
		Date date = new Date();
		System.out.println(sdf.format(date));
 }
}
