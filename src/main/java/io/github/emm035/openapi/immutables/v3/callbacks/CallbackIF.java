package io.github.emm035.openapi.immutables.v3.callbacks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.emm035.openapi.immutables.v3.PathItem;
import io.github.emm035.openapi.immutables.v3.references.Referenceable;
import io.github.emm035.openapi.immutables.v3.shared.Extensible;
import io.github.emm035.openapi.immutables.v3.shared.OpenApiStyle;
import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;

@Immutable
@OpenApiStyle
@JsonSerialize(using = CallbackSerializer.class)
@JsonDeserialize(using = CallbackDeserializer.class)
public abstract class CallbackIF implements Extensible, Referenceable<Callback> {
  public abstract String getExpression();
  public abstract PathItem getPathItem();

  @Check
  CallbackIF normalizeExtensions() {
    if (Checks.allValid(this)) {
      return this;
    }
    return Callback.builder()
      .from(this)
      .setExtensions(Checks.validExtensions(this))
      .build();
  }
}
