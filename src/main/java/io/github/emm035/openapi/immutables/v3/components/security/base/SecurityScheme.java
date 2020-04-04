package io.github.emm035.openapi.immutables.v3.components.security.base;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import io.github.emm035.openapi.immutables.v3.base.Extensible;
import io.github.emm035.openapi.immutables.v3.components.base.Describable;
import io.github.emm035.openapi.immutables.v3.components.base.Typed;
import io.github.emm035.openapi.immutables.v3.components.security.ApiKeyScheme;
import io.github.emm035.openapi.immutables.v3.components.security.HttpScheme;
import io.github.emm035.openapi.immutables.v3.components.security.OAuth2Scheme;
import io.github.emm035.openapi.immutables.v3.components.security.OpenIdConnectScheme;

import static com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, property = "type")
@JsonSubTypes({
  @Type(value = ApiKeyScheme.class, name = "apiKey"),
  @Type(value = HttpScheme.class, name = "http"),
  @Type(value = OAuth2Scheme.class, name = "oauth2"),
  @Type(value = OpenIdConnectScheme.class, name = "openIdConnect")
})
public interface SecurityScheme extends Extensible, Describable, Typed<SecurityScheme.Type> {

  enum Type {
    API_KEY,
    HTTP,
    OAUTH2,
    OPEN_ID_CONNECT
    ;

    private static final Converter<String, String> TO_JSON_CASE_CONVERTER = CaseFormat.UPPER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL);
    private static final Converter<String, String> FROM_JSON_CASE_CONVERTER = CaseFormat.UPPER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL);

    @JsonValue
    private String toJson() {
      return TO_JSON_CASE_CONVERTER.convert(name());
    }

    @JsonAnyGetter
    private static Type fromJson(@JsonProperty String rawValue) {
      return Type.valueOf(FROM_JSON_CASE_CONVERTER.convert(rawValue));
    }
  }
}
