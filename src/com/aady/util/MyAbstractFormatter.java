package com.aady.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class MyAbstractFormatter extends AbstractFormatter {

	final SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String valueToString(Object value) throws ParseException {
		Calendar cal = Calendar.getInstance();
		if (value != null) {
			cal = (Calendar) value;
		}

		String formatted = format1.format(cal.getTime());
		return formatted;

	}

	@Override
	public Object stringToValue(String text) throws ParseException {
		return format1.parse(text);

	}
}
