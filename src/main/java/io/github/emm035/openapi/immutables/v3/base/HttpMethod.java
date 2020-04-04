package io.github.emm035.openapi.immutables.v3.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum HttpMethod {
  GET,PUT,POST,DELETE,OPTIONS,HEAD,PATCH,TRACE;

  @JsonCreator
  private static HttpMethod fromString(@JsonProperty String value) {
    return HttpMethod.valueOf(value.toUpperCase());
  }
}
