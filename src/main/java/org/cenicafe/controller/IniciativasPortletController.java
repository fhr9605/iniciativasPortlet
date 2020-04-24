package org.cenicafe.controller;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.PartialResultException;

import org.apache.log4j.Logger;
import org.cenicafe.model.Empleado;
import org.cenicafe.model.Iniciativa;
import org.cenicafe.model.Pregunta;
import org.cenicafe.repository.IWebServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view") // El scope view fue creado por una clase custom.
@PropertySource("classpath:servicios.properties")

public class IniciativasPortletController implements Serializable{
	
	final static Logger logger = Logger.getLogger(IniciativasPortletController.class);

	/***
	 * ATRIBUTOS
	 */
			// CLASE EMPLEADO 
			private Empleado usuario;
			
			// CLASE INICIATIVA
			private Iniciativa nuevaIniciativa;
			private Iniciativa iniciativaSeleccionada;
			private List<Iniciativa> lstInicitivas;
			
			//CLASE PREGUNTA
			private Pregunta pregunta;
			private List<Pregunta> lstPreguntas;

			//CLASE CONTROLLER
			private String emailContacto;
			private List<String> lstProgramas;
			private List<String> lstSubProgramas;
			private List<String> lstEjeEstrategico;
			
			// CLASE BOOLEAN 
			private boolean mostrarIniciativas;
			private boolean mostrarFormularioIniciativas;
			private boolean botonAgregarIniciativa;
	/***
	 *  GETTER AND SETTER 
	 */
			// GETTER AND SETTER DE LOS OBJETOS DE LA CLASE EMPLEADOS 
			
			public Empleado getUsuario() {
				return usuario;
			}
			public void setUsuario(Empleado usuario) {
				this.usuario = usuario;
			}
			
			// GETTER AND SETTER DE LOS OBJETOS DE LA CLASE INICIATIVA 
			
			public Iniciativa getNuevaIniciativa() {
				return nuevaIniciativa;
			}
			public void setNuevaIniciativa(Iniciativa nuevaIniciativa) {
				this.nuevaIniciativa = nuevaIniciativa;
			}
			
			public Iniciativa getIniciativaSeleccionada() {
				return iniciativaSeleccionada;
			}
			public void setIniciativaSeleccionada(Iniciativa iniciativaSeleccionada) {
				this.iniciativaSeleccionada = iniciativaSeleccionada;
			}
			
			public List<Iniciativa> getLstInicitivas() {
				return lstInicitivas;
			}
			public void setLstInicitivas(List<Iniciativa> lstInicitivas) {
				this.lstInicitivas = lstInicitivas;
			}
			
			// GETTER AND SETTER DE LOS OBJETOS DE LA CLASE PREGUNTA 
			
			public Pregunta getPregunta() {
				return pregunta;
			}
			public void setPregunta(Pregunta pregunta) {
				this.pregunta = pregunta;
			}
			
			public List<Pregunta> getLstPreguntas() {
				return lstPreguntas;
			}
			public void setLstPreguntas(List<Pregunta> lstPreguntas) {
				this.lstPreguntas = lstPreguntas;
			}
			
			// GETTER AND SETTER DE LOS OBJETOS DE LA CLASE CONTROLLER
			public String getEmailContacto() {
				return emailContacto;
			}
			public void setEmailContacto(String emailContacto) {
				this.emailContacto = emailContacto;
			}
			
			public List<String> getLstProgramas() {
				return lstProgramas;
			}
			public void setLstProgramas(List<String> lstProgramas) {
				this.lstProgramas = lstProgramas;
			}
			
			public List<String> getLstSubProgramas() {
				return lstSubProgramas;
			}
			public void setLstSubProgramas(List<String> lstSubProgramas) {
				this.lstSubProgramas = lstSubProgramas;
			}
			
			public List<String> getLstEjeEstrategico() {
				return lstEjeEstrategico;
			}
			public void setLstEjeEstrategico(List<String> lstEjeEstrategico) {
				this.lstEjeEstrategico = lstEjeEstrategico;
			}
			
			// GETTER AND SETTER DE LOS OBJETOS BOOLEAN 
			public boolean isMostrarIniciativas() {
				return mostrarIniciativas;
			}
			public void setMostrarIniciativas(boolean mostrarIniciativas) {
				this.mostrarIniciativas = mostrarIniciativas;
			}
			
			public boolean isMostrarFormularioIniciativas() {
				return mostrarFormularioIniciativas;
			}
			public void setMostrarFormularioIniciativas(boolean mostrarFormularioIniciativas) {
				this.mostrarFormularioIniciativas = mostrarFormularioIniciativas;
			}
			
			public boolean isBotonAgregarIniciativa() {
				return botonAgregarIniciativa;
			}
			public void setBotonAgregarIniciativa(boolean botonAgregarIniciativa) {
				this.botonAgregarIniciativa = botonAgregarIniciativa;
			}
			
			
			
		@Autowired
		IWebServices ws;
		
	/***
	 * METODOS ---> INIT 
	 */
			//METODO ---> INIT ()
			@PostConstruct
			public void init () {
				logger.info("Hola Init ---> Portlet de Iniciativas");
				
				setUsuario(new Empleado());
				setNuevaIniciativa(new Iniciativa());
				setIniciativaSeleccionada(new Iniciativa());
				setLstInicitivas(new ArrayList<>());
				setPregunta(new Pregunta());
				setLstPreguntas(new ArrayList<>());
				
				
				setLstProgramas(new ArrayList<>());
				setLstSubProgramas(new ArrayList<>());
				setLstEjeEstrategico(new ArrayList<>());
				
				setMostrarIniciativas(true);
				setMostrarFormularioIniciativas(false);
				setBotonAgregarIniciativa(true);
				
				
				setLstProgramas(agregarProgramas());
				setLstSubProgramas(agregarSubProgramas());
				setLstEjeEstrategico(agregarEjeEstrategico());

				try {
					//obtenerEmailContacto();
					//setUsuario(ws.RetornarEmpleado(getEmailContacto()));
					setLstPreguntas(ws.obtenerPreguntas());
				} catch (Exception e) {
					
					logger.error(e);
					// TODO: handle exception
				}
			}
			
		/***
		 * METODOS --->  GENERAR // CONVERTIR  // MENSAJES // OBTENER EMAIL
		 */
			
			// METDODO ---> AGREGAR MENSAJE DE INFORMACION 
			public void addMessageInfo(String summary) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
				FacesContext.getCurrentInstance().addMessage("msgsInfo", message);
			}
			
			// METODO ---> AGREGAR MENSAJE DE ERROR 
			public void addMessageError(String summary) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
				FacesContext.getCurrentInstance().addMessage("msgsError", message);
			}
		
			// METODO ---> OBTENER EMAIL DE CONTACTO 
			public void obtenerEmailContacto() throws ParseException, PortalException, SystemException {
	
		        String Login = getCurrentUser().getLogin();
		        if (Login.equals("default@liferay.com")) {
		            Login = null;
		        } else if (Login.trim().length() == 0) {
		            Login = null;
		        }
		        setEmailContacto(Login);
		    }
			
			// METODO ---> OBTENER USUARIO 
			public User getCurrentUser(){
		        User u = null;
		        FacesContext fc = FacesContext.getCurrentInstance();
		
		        ExternalContext externalContext = fc.getExternalContext();
		
		        if (externalContext.getUserPrincipal() == null) {
	        	} else {
		            Long id = Long.parseLong(externalContext.getUserPrincipal().getName());
		            try {
		               u = UserLocalServiceUtil.getUserById(id);
	            	} catch (PortalException ex) {
	            		Logger.getLogger(IniciativasPortletController.class.getName()).log(null, ex);
	            	} catch (SystemException ex) {
	            		Logger.getLogger(IniciativasPortletController.class.getName()).log(null, ex);
	            	}
	        	}
		       return u;
			}
		
			//METODO ---> GENERA LA FECHA ACTUAL
			public String fechaActualString() {
				Date fecha = new Date();
				SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
				
				return formatoFecha.format(fecha);
			}
			
		/***
		 * METODO ---> VALIDAR // AGREGAR 
		 */
		
			//METODO ---> GURDAR INICIATVA 
			public void guardarIniciativa() {
				getNuevaIniciativa().setIdIniciativa("1"+getNuevaIniciativa().getPrograma());
				getNuevaIniciativa().setFechaCreacion(fechaActualString());
				getNuevaIniciativa().setEstado("Registrada ---> ID 20 ");
				getNuevaIniciativa().setPreguntasIniciativa(getLstPreguntas());
				getLstInicitivas().add(getNuevaIniciativa());
				setNuevaIniciativa(new Iniciativa());
				addMessageInfo(" La iniciativa fue generada correctamenta ");
				cargarInicial();
			}
			
			//METODO ---> VALIDAR LAS INICIATIVAS 
			public String validarIniciativas() {
				Iniciativa iniciativaValidar= getNuevaIniciativa();
				
				for(Pregunta objPregunta:getLstPreguntas()) {
					if(objPregunta.getRespuesta()==null) {
						addMessageInfo("Debe completar cada pregunta para adicionar la iniciativa ");
						return null;
					}
				}
			
				if ((iniciativaValidar.getPrograma()==null) || (iniciativaValidar.getSubPrograma()==null) ||
					(iniciativaValidar.getEjeEstrategico()==null) || (iniciativaValidar.getTituloIniciativa().equals(""))) {
					addMessageInfo("Debe llenar todos los campos ");
					return null;
				}else{
					guardarIniciativa();
					return null;
				}	
			}
			
			
		/***
		 * METODO ---> PRUEBAS 
		 */
			
			// METODO ---> AGREGAR PROGRAMAS PRUEBA
			public List<String> agregarProgramas(){
				List<String> lstDisiplinas= new ArrayList<>();
				
				lstDisiplinas.add("El programa va ir tomado de los datos del ususario");
				return lstDisiplinas;
			}
			
			// METODO ---> AGREGAR SUB PROGRAMAS PRUEBA 
			public List<String> agregarSubProgramas(){
				List<String> lstDisiplinas= new ArrayList<>();
				
				for (int i=0;i<4;i++) {
					lstDisiplinas.add("SubPrograma # " + i);
				}
				return lstDisiplinas;
			}
			
			// METODO ---> AGREGAR EJE ESTRATEGICO PRUEBA 
			public List<String> agregarEjeEstrategico(){
				List<String> lstDisiplinas= new ArrayList<>();
				
				for (int i=0;i<4;i++) {
					lstDisiplinas.add(" eje estrategico # " + i);
				}
				return lstDisiplinas;
			}

		/***
		 * METODOS ---> RENDERED
		 */
		
			//METODO ---> CARGA INICIAL 
			public void cargarFormularioIniciativas() {
				setMostrarIniciativas(false);
				setMostrarFormularioIniciativas(true);
				setBotonAgregarIniciativa(false);
			}
			
			//METODO ---> CARGA INICIAL 
			public void cargarInicial() {
				setMostrarIniciativas(true);
				setMostrarFormularioIniciativas(false);
				setBotonAgregarIniciativa(true);
			}
}
