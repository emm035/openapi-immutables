package io.github.emm035.openapi.immutables.v3.references;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@JsonDeserialize(using = ReferenceableDeserializer.class)
public interface Referenceable<T> {
  @JsonIgnore
  @Value.Derived
  default boolean isReferential() {
    return false;
  }
}
