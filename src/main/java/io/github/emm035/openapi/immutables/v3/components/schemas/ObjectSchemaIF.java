package io.github.emm035.openapi.immutables.v3.components.schemas;

import io.github.emm035.openapi.immutables.v3.base.OpenApiStyle;
import io.github.emm035.openapi.immutables.v3.components.RefOr;
import io.github.emm035.openapi.immutables.v3.components.schemas.base.Schema;
import io.github.emm035.openapi.immutables.v3.components.schemas.base.TypedSchema;
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

  Map<String, RefOr<Schema>> getProperties();
  List<String> getRequired();
  Optional<RefOr<Schema>> getAdditionalProperties();
}
