package org.cenicafe.model;

public class Empleado {
	
	private String cedula;
	private String nombres;
	private String mailCorporativo;
	private String programa;
	private String extension;
	private String foto;
	

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getnombres() {
		return nombres;
	}

	public void setnombres(String nombres) {
		this.nombres = nombres;
	}

	public String getmailCorporativo() {
		return mailCorporativo;
	}

	public void setmailCorporativo(String mailCorporativo) {
		this.mailCorporativo = mailCorporativo;
	}

	public String getprograma() {
		return programa;
	}

	public void setprograma(String programa) {
		this.programa = programa;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}

}