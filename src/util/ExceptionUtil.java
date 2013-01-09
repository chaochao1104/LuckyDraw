package util;

public class ExceptionUtil {

	public static String getStackTrace(Exception e) {
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement stackTraceElement : e.getStackTrace())
			sb.append(stackTraceElement.toString() + "\n");
		
		return sb.toString();
	}
}
