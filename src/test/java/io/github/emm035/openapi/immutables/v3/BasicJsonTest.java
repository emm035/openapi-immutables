package io.github.emm035.openapi.immutables.v3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.io.ByteStreams;
import io.github.emm035.openapi.immutables.v3.jackson.Json;
import io.github.emm035.openapi.immutables.v3.servers.Server;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BasicJsonTest<T> {

  public abstract Class<T> getModelClass();

  public abstract T getInstance();

  @Test
  public void fromJson_deserializesToExpectedFields() {
    var loadedObject = load(getModelClass());
    assertThat(loadedObject).isEqualTo(getInstance());
  }

  @Test
  public void toJson_serializesToExpectedFields() throws JsonProcessingException {
    var loadedJson = load(JsonNode.class);
    var serialized = Json.toTree(getInstance());
    assertThat(serialized).isEqualTo(loadedJson);
  }

  protected <T> T load(Class<T> clazz) {
    try {
      var stream = BasicJsonTest.class.getClassLoader().getResourceAsStream(getModelClass().getSimpleName() + ".json");
      return Json.MapperFactory.getInstance().readValue(new String(ByteStreams.toByteArray(stream)), clazz);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
