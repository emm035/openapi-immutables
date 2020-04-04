package io.github.emm035.openapi.immutables.v3.components.content;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.google.common.base.Preconditions;
import io.github.emm035.openapi.immutables.v3.base.Extensible;
import io.github.emm035.openapi.immutables.v3.base.OpenApiStyle;
import io.github.emm035.openapi.immutables.v3.components.RefOr;
import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;

import java.util.Map;
import java.util.Optional;


@OpenApiStyle
@Immutable
public abstract class AbstractResponses implements Extensible {
  public abstract Optional<RefOr<Response>> getDefault();
  @JsonUnwrapped
  public abstract Map<Integer, RefOr<Response>> getResponses();

  @Check
  private Responses normalizeExtensions(Responses extensible) {
    Preconditions.checkState(getDefault().isPresent() || !getResponses().isEmpty(), "At least one response must be specified");

    if (Checks.allValid(extensible)) {
      return extensible;
    }
    return Responses.builder()
      .from(extensible)
      .setExtensions(Checks.validExtensions(extensible))
      .build();
  }
}
