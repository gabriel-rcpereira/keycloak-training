package com.example.keycloak.config.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

//@Configuration
public class OrganizationSubClaimAdapter extends JwtAccessTokenConverter implements Converter<Map<String, Object>, Map<String, Object>> {

    private final MappedJwtClaimSetConverter delegate = MappedJwtClaimSetConverter
            .withDefaults(Collections.emptyMap());

    private final ObjectMapper mapper;

    public OrganizationSubClaimAdapter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Map<String, Object> convert(Map<String, Object> claims) {
        Map<String, Object> convertedClaims = this.delegate.convert(claims);
        String username = Optional.ofNullable(convertedClaims.get("username"))
                .map(Object::toString)
                .orElse("");

        return null;
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> tokenMap) {
        JsonNode token = mapper.convertValue(tokenMap, JsonNode.class);

        return null;
    }
}
