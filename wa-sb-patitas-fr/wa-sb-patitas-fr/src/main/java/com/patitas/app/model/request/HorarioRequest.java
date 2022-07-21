package com.patitas.app.model.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioRequest {
	private static Logger lo = LogManager.getLogger(HorarioRequest.class);
	
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ss";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	
	private Integer idVet;
	private String manInicio;
	private String manFin;
	private String tarInicio;
	private String tarFin;

	
	public String convertirHoraADate(String hora) throws ParseException{
		@SuppressWarnings("deprecation")
		Date hs = new Date(0000,00,00,Integer.parseInt(hora.substring(0,2)),Integer.parseInt(hora.substring(3)),0);
		String formato = sdf.format(hs);
		lo.info(formato);
		return formato;
	}

	@Override
	public String toString() {
		return "HorarioRequest{" +
				"sdf=" + sdf +
				", idVet=" + idVet +
				", manInicio='" + manInicio + '\'' +
				", manFin='" + manFin + '\'' +
				", tarInicio='" + tarInicio + '\'' +
				", tarFin='" + tarFin + '\'' +
				'}';
	}
}
