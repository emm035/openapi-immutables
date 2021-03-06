package io.github.emm035.openapi.immutables.v3.links;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.emm035.openapi.immutables.v3.shared.Describable;
import io.github.emm035.openapi.immutables.v3.shared.Extensible;
import io.github.emm035.openapi.immutables.v3.shared.OpenApiStyle;
import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;

import java.util.Map;
import java.util.Optional;


@OpenApiStyle
@Immutable
@JsonDeserialize
public abstract class AbstractIdLink implements Link, Extensible, Describable {
  public abstract String getOperationId();
  public abstract Map<String, Object> getParameters();
  public abstract Optional<Object> getRequestBody();

  @Check
  AbstractIdLink normalizeExtensions() {
    if (Checks.allValid(this)) {
      return this;
    }
    return IdLink.builder()
      .from(this)
      .setExtensions(Checks.validExtensions(this))
      .build();
  }
}
