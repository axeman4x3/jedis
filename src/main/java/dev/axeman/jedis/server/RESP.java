package dev.axeman.jedis.server;

public class RESP {
	public static String nullBulkString() {
		return "$-1\r\n";
	}
}
