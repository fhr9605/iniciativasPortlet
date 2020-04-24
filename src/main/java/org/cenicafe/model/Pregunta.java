package org.cenicafe.model;

import java.util.List;

public class Pregunta {
	
	private String nroPregunta;
	private String pregunta;
	private String respuesta;
	private String tiporespuesta;
	private String[]opciones;
	
	public String getNroPregunta() {
		return nroPregunta;
	}
	public void setNroPregunta(String nroPregunta) {
		this.nroPregunta = nroPregunta;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getTiporespuesta() {
		return tiporespuesta;
	}
	public void setTiporespuesta(String tiporespuesta) {
		this.tiporespuesta = tiporespuesta;
	}
	public String[] getOpciones() {
		return opciones;
	}
	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}
}
