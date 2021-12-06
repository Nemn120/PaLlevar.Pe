package com.paLlevar.app.security;

import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private UserService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();

		UserEntity usuario = usuarioService.getUserByUsername(authentication.getName());
		info.put("name", usuario.getNombre());
		info.put("lastName", usuario.getLastName());
		info.put("documentNumber", usuario.getDocumentNumber());
		info.put("cellPhone", usuario.getCellPhone());
		info.put("documentTypeId", usuario.getDocumentNumber());
		info.put("profileId", usuario.getProfile().getIdProfile());
		info.put("profileName", usuario.getProfile().getName());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

		return accessToken;
	}

}
