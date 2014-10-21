package com.egoday.persongenerator.service.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Lee las lineas de un fichero y devuelve un array de strings.
 */
public class Lines {
	
	private static final String CHARSET_NAME = "utf-8";
	
	public static List<String> fromStream(InputStream inputStream, double linesPercentage) {
		List<String> lines = new ArrayList<String>();
		
		try (BufferedReader reader = getReader(inputStream)) {
			String line = reader.readLine();

			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		double percentage = linesPercentage * linesPercentage * linesPercentage / 1000000;
		int toIndex = (int) Math.round(lines.size() * percentage);
		toIndex = Math.max(1, toIndex);

		return lines.subList(0, toIndex);
	}

	private static BufferedReader getReader(InputStream inputStream) throws Exception {
		Charset charset = Charset.forName(CHARSET_NAME);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);

		return new BufferedReader(inputStreamReader);
	}
}