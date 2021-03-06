package io.github.emm035.openapi.immutables.v3.schemas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.CaseFormat;
import io.github.emm035.openapi.immutables.v3.shared.OpenApiStyle;
import org.immutables.value.Value.Check;
import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;

import java.util.Optional;


@OpenApiStyle
@Immutable
public abstract class AbstractIntegerSchema implements NumericSchema<Long> {
  @Override
  @Derived
  public Type getType() {
    return Type.INTEGER;
  }

  public abstract Optional<Format> getFormat();

  public static enum Format {
    INT32,
    INT64;

    @JsonCreator
    private static Format fromString(@JsonProperty String rawValue) {
      return valueOf(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, rawValue));
    }

    @Override
    @JsonValue
    public String toString() {
      return name().toLowerCase();
    }
  }

  @Check
  AbstractIntegerSchema normalizeExtensions() {
    if (Checks.allValid(this)) {
      return this;
    }
    return IntegerSchema.builder()
      .from(this)
      .setExtensions(Checks.validExtensions(this))
      .build();
  }
}
