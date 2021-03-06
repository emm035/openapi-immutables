package io.github.emm035.openapi.immutables.v3.schemas;

import io.github.emm035.openapi.immutables.v3.references.Referenceable;
import io.github.emm035.openapi.immutables.v3.shared.OpenApiStyle;
import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@OpenApiStyle
@Immutable
public interface ObjectSchemaIF extends TypedSchema {
  @Override
  @Default
  default Type getType() {
    return Type.OBJECT;
  }

  Map<String, Referenceable<Schema>> getProperties();
  List<String> getRequired();
  Optional<Referenceable<Schema>> getAdditionalProperties();
}
