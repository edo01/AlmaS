package Alma.util;

import java.io.BufferedReader;
import java.io.IOException;

public class PersistenceCleaner {
	
	private PersistenceCleaner() {}
	
	public static String readLineSkippingEmpty(BufferedReader innerReader) throws IOException {
		String line;
		do { // skips empty lines
			line = innerReader.readLine();
		} while (line != null && line.trim().isEmpty());
		return line;
	}
}
