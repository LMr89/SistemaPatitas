package com.patitas.api.app.infraestructura.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateACalendar {
	@SuppressWarnings("unused")
	private static  final SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
	
	public static Calendar convertir(Date objDate) {
		return Calendar.getInstance();
	}
	

}
