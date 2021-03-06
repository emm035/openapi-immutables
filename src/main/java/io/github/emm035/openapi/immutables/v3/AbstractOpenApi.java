package io.github.emm035.openapi.immutables.v3;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import io.github.emm035.openapi.immutables.v3.metadata.ExternalDocumentation;
import io.github.emm035.openapi.immutables.v3.metadata.Info;
import io.github.emm035.openapi.immutables.v3.security.SecurityRequirement;
import io.github.emm035.openapi.immutables.v3.servers.Server;
import io.github.emm035.openapi.immutables.v3.shared.Extensible;
import io.github.emm035.openapi.immutables.v3.shared.OpenApiStyle;
import org.immutables.value.Value.Check;
import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;

import java.util.List;
import java.util.Optional;

@Immutable
@OpenApiStyle
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class AbstractOpenApi implements Extensible {
  @JsonProperty("openapi")
  public abstract String getOpenApi();
  public abstract Info getInfo();

  @Default
  public List<Server> getServers() {
    return ImmutableList.of(Server.of("/"));
  }

  public abstract Optional<Components> getComponents();
  public abstract List<SecurityRequirement> getSecurity();
  public abstract List<Tag> getTags();
  public abstract Optional<ExternalDocumentation> getExternalDocs();
  @Default
  public Paths getPaths() {
    return Paths.empty();
  }

  @Check
  AbstractOpenApi normalizeExtensions() {
    if (Checks.allValid(this)) {
      return this;
    }
    return OpenApi.builder()
      .from(this)
      .setExtensions(Checks.validExtensions(this))
      .build();
  }
}
