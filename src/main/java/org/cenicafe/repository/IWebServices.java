package org.cenicafe.repository;

import java.util.List;

import org.cenicafe.model.Empleado;
import org.cenicafe.model.Pregunta;

public interface IWebServices {

	Empleado RetornarEmpleado(String email) throws Exception;
	List<Pregunta> obtenerPreguntas() throws Exception;

}
