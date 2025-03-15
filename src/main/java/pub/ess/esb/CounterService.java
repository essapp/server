package pub.ess.esb;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;

/**
 * Counts access to the WEB service.
 */
public class CounterService implements HttpService {

  private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());
  private final LongAdder allAccessCounter = new LongAdder();
  private final AtomicInteger apiAccessCounter = new AtomicInteger();

  @Override
  public void routing(HttpRules rules) {
    rules.any(this::handleAny)
        .get("/api/counter", this::handleGet);
  }

  private void handleAny(ServerRequest request, ServerResponse response) {
    allAccessCounter.increment();
    response.next();
  }

  private void handleGet(ServerRequest request, ServerResponse response) {
    int apiAcc = apiAccessCounter.incrementAndGet();
    JsonObject result = JSON.createObjectBuilder()
        .add("all", allAccessCounter.longValue())
        .add("api", apiAcc)
        .build();
    response.send(result);
  }
}
