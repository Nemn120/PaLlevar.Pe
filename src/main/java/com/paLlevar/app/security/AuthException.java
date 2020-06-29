package com.paLlevar.app.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthException implements AuthenticationEntryPoint {
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
			throws IOException, ServletException {
		final Map<String, Object> mapException = new HashMap<>();

		mapException.put("error", "401");
		mapException.put("message", "No estas autorizado para acceder a este recurso");
		mapException.put("exception", "No autorizado");
		mapException.put("path", request.getServletPath());
		mapException.put("timestamp", (new Date()).getTime());

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), mapException);
	}
	
	
}