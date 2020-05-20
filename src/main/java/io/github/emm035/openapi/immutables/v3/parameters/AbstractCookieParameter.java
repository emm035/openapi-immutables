package io.github.emm035.openapi.immutables.v3.parameters;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.emm035.openapi.immutables.v3.shared.OpenApiStyle;
import org.immutables.value.Value;
import org.immutables.value.Value.Check;
import org.immutables.value.Value.Derived;

import java.util.Optional;


@OpenApiStyle
@Value.Immutable
public abstract class AbstractCookieParameter implements Parameter {
  @Override
  @Derived
  public Location getIn() {
    return Location.COOKIE;
  }

  public abstract Optional<Style> getStyle();
  public abstract Optional<Boolean> getExplode();

  @Derived
  @JsonIgnore
  public Style getStyleOrDefault() {
    return getStyle().orElse(Style.FORM);
  }

  @Derived
  @JsonIgnore
  public boolean getExplodeOrDefault() {
    return getExplode().orElse(true);
  }

  public static enum Style {
    FORM;

    @JsonCreator
    private static Style fromString(@JsonProperty String rawValue) {
      return valueOf(rawValue.toUpperCase());
    }

    @Override
    @JsonValue
    public String toString() {
      return name().toLowerCase();
    }
  }

  @Check
  AbstractCookieParameter normalizeExtensions() {
    if (Checks.allValid(this)) {
      return this;
    }
    return CookieParameter.builder()
      .from(this)
      .setExtensions(Checks.validExtensions(this))
      .build();
  }
}
