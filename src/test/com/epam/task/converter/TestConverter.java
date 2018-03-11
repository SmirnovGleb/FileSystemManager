package test.com.epam.task.converter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.epam.task.util.PathConverter.*;

public class TestConverter {
	private static final String EXPECTED_STRING = "D:\\app\\";
	private static final String EXPECTED_CONVERTED_PATH = "D:<app<";
	private static final List<String> EXPECTED_LIST = new ArrayList<>();

	@BeforeClass
	public static void setup() {
		EXPECTED_LIST.add("D:");
		EXPECTED_LIST.add("app");

	}

	@Test
	public void testConvert() {
		Assert.assertEquals(EXPECTED_STRING, convert(EXPECTED_CONVERTED_PATH));
	}

	@Test
	public void testConvertBack() {
		Assert.assertEquals(EXPECTED_CONVERTED_PATH, convertBack(EXPECTED_STRING));
	}

	@Test
	public void testStringToList() {
		Assert.assertEquals(EXPECTED_LIST, stringToList(EXPECTED_STRING));
	}

	@Test
	public void testStringToListWithWrongData() {
		Assert.assertNotEquals(stringToList("sdfsdf"), EXPECTED_LIST);
	}

	@Test(expected = NullPointerException.class)
	public void testStringToListExpectedException() {
		stringToList(null);
	}

	@Test(expected = NullPointerException.class)
	public void testConvertExpectedException() {
		convert(null);
	}

	@Test(expected = NullPointerException.class)
	public void testConvertBackExpectedException() {
		convertBack(null);
	}

}
