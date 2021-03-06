package io.github.emm035.openapi.immutables.v3.schemas;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.emm035.openapi.immutables.v3.references.Referenceable;
import io.github.emm035.openapi.immutables.v3.shared.OpenApiStyle;
import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;

import java.util.List;


@OpenApiStyle
@Immutable
// Necessary to override deserializer for io.github.emm035.open.api.core.v3.components.schemas.Schema
@JsonDeserialize
public abstract class AbstractAnyOfSchema implements Schema {
  public abstract List<Referenceable<Schema>> getAnyOf();

  public static AnyOfSchema of(Referenceable<Schema>... schemas) {
    return AnyOfSchema.builder()
      .addAnyOf(schemas)
      .build();
  }

  @Check
  AbstractAnyOfSchema normalizeExtensions() {
    if (Checks.allValid(this)) {
      return this;
    }
    return AnyOfSchema.builder()
      .from(this)
      .setExtensions(Checks.validExtensions(this))
      .build();
  }
}
