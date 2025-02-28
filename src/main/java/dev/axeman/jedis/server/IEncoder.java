package dev.axeman.jedis.server;

public interface IEncoder {
	String encode(String data, Encode encode);
}
