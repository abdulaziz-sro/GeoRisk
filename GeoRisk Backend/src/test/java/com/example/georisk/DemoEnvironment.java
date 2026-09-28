package com.example.georisk;

public final class DemoEnvironment {
	
	private DemoEnvironment() {
		
	}
	
	public static String environment() {
		return System.getProperty("test.environment", "lokal");
	}
	
	public static String geoServerUrl() {
		return System.getProperty("geoserver.url", "http://localhost:8080/geoserver");
	}
	
	public static String geoServerVersion() {
		return System.getProperty("geoserver.version", "lokal");
	}
	
	public static String lwasUrl() {
		return System.getProperty("lwas.url", "https://localhost:8443");
	}
	
	public static String lwasVersion() {
		return System.getProperty("lwas.version", "lokal");
	}
}
