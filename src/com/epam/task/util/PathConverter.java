package com.epam.task.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The Class Converter.
 */
public class PathConverter {
	
	private static final String PATH_SEPARATOR = "\\\\";
	
	private static final String SLASH_SUBSTITUTOR = "<";
	
	private static final String DOT_SUBSTITUTOR = ">";
	
	private static final String DOT = ".";
	
	private static final String DOT_REGEX = "[.]";

	/**
	 * Instantiates a new converter.
	 */
	private PathConverter() {
	}

	/**
	 * Converts String to List.
	 *
	 * @param path the path
	 * @return the list
	 */
	public static List<String> stringToList(final String path) {
		return new ArrayList<String>(Arrays.asList(path.split(PATH_SEPARATOR)));
	}

	/**
	 * Converts Contract Path String from UI to Absolute Path String for File System.
	 *
	 * @param String path 
	 * @return the string
	 */
	public static String convert(final String path) {
		final String replaceSlash = path.replaceAll(SLASH_SUBSTITUTOR, PATH_SEPARATOR);
		final String replaceDot = replaceSlash.replaceAll(DOT_SUBSTITUTOR, DOT);
		return replaceDot;
	}

	/**
	 * Converts File System Absolute Path String to Contract Path String for UI.
	 *
	 * @param String path 
	 * @return the string
	 */
	public static String convertBack(final String path) {
		final String replaceSlash = path.replaceAll(PATH_SEPARATOR, SLASH_SUBSTITUTOR);
		final String replaceDot = replaceSlash.replaceAll(DOT_REGEX, DOT_SUBSTITUTOR);
		return replaceDot;
	}
}
