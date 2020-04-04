package io.github.emm035.openapi.immutables.v3.components.content;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.emm035.openapi.immutables.v3.base.Extensible;
import io.github.emm035.openapi.immutables.v3.base.OpenApiStyle;
import org.immutables.value.Value.Immutable;

import java.util.Map;


@Immutable
@OpenApiStyle
@JsonSerialize(using = ContentSerializer.class)
@JsonDeserialize(using = ContentDeserializer.class)
public abstract class AbstractContent implements Extensible {
  @JsonUnwrapped
  public abstract Map<String, MediaType> getMediaTypes();
}
