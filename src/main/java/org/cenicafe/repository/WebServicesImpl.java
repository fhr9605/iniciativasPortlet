package org.cenicafe.repository;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.cenicafe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;



@Repository
@PropertySource("classpath:servicios.properties")
public class WebServicesImpl implements IWebServices {

final static Logger logger = Logger.getLogger(WebServicesImpl.class);
	
	@Autowired
	Environment env;
	
	
	@Override		
	public Empleado RetornarEmpleado(String email) throws Exception{
		RestTemplate empleado = new RestTemplate();
		URL url = new URL(env.getProperty("WebService.Server")+"/WsSIGA_Empleados/siga/obtenerInformacionEmpleadoMail/"+email);
		
		return (Empleado)empleado.getForObject(url.toURI(), Empleado.class);					
		
	}
	
	@Override
    public List<Pregunta> obtenerPreguntas () throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Pregunta>> response = restTemplate.exchange("http://192.168.194.119:8080/jsons/preguntas.json",
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<List<Pregunta>>(){});
		List<Pregunta> lstPreguntas = response.getBody();

		return lstPreguntas;
	}
}
