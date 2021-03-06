package io.github.emm035.openapi.immutables.v3.schemas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.emm035.openapi.immutables.v3.shared.Describable;
import io.github.emm035.openapi.immutables.v3.shared.WithSingleExample;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, property = "type")
@JsonSubTypes(value = {
  @JsonSubTypes.Type(value = ObjectSchema.class, name = "object"),
  @JsonSubTypes.Type(value = ArraySchema.class, name = "array"),
  @JsonSubTypes.Type(value = IntegerSchema.class, name = "integer"),
  @JsonSubTypes.Type(value = NumberSchema.class, name = "number"),
  @JsonSubTypes.Type(value = StringSchema.class, name = "string"),
  @JsonSubTypes.Type(value = BooleanSchema.class, name = "boolean")
})
// Necessary to override deserializer for io.github.emm035.open.api.core.v3.components.schemas.Schema
@JsonDeserialize
public interface TypedSchema extends Schema, Describable, WithSingleExample {
  Type getType();

  static enum Type {
    ARRAY,
    STRING,
    NUMBER,
    OBJECT,
    INTEGER,
    BOOLEAN;

    @JsonCreator
    public static Type fromString(@JsonProperty String rawValue) {
      return valueOf(rawValue.toUpperCase());
    }

    @Override
    @JsonValue
    public String toString() {
      return name().toLowerCase();
    }
  }
}
