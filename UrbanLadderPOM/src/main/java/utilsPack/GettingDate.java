package utilsPack;

import java.util.Date;

public class GettingDate {
	// It returns current data and time
	public static String getdate() {
		Date date = new Date();
		return date.toString().replaceAll(" ", "_").replaceAll(":", "_");
	}
}

