package pub.ess.esb;

import io.helidon.http.Status;
import io.helidon.webclient.api.ClientResponseTyped;
import io.helidon.webclient.http1.Http1Client;
import io.helidon.webclient.http1.Http1ClientResponse;
import io.helidon.webserver.http.HttpRouting;
import io.helidon.webserver.testing.junit5.SetUpRoute;

import org.junit.jupiter.api.Test;

import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.CoreMatchers.containsString;

abstract class AbstractMainTest {
  private final Http1Client client;

  protected AbstractMainTest(Http1Client client) {
    this.client = client;
  }

  @SetUpRoute
  static void routing(HttpRouting.Builder builder) {
    Main.routing(builder);
  }

  @Test
  void testUi() {
    assertThat(allCounter(), is(1));
    try (Http1ClientResponse response = client.get("/index.html").request()) {
      assertThat(response.status(), is(Status.OK_200));
      assertThat(response.headers().contentType().orElseThrow().text(), is("text/html"));
    }
    try (Http1ClientResponse response = client.get("/loding.js").request()) {
      assertThat(response.status(), is(Status.OK_200));
      assertThat(response.headers().contentType().orElseThrow().text(), is("text/javascript"));
    }
    assertThat(allCounter(), is(5)); // includes /api/counter calls
  }

  @Test
  void testGreeting() {
    ClientResponseTyped<JsonObject> response = client.get("/greet").request(JsonObject.class);
    assertThat(response.status(), is(Status.OK_200));
    assertThat(response.entity().getString("message"), is("Hello World!"));
  }

  @Test
  void testMetricsObserver() {
    try (Http1ClientResponse response = client.get("/observe/metrics").request()) {
      assertThat(response.status(), is(Status.OK_200));
    }
  }

  @Test
  void testSimpleGreet() {
    ClientResponseTyped<String> response = client.get("/simple-greet").request(String.class);
    assertThat(response.status(), is(Status.OK_200));
    assertThat(response.entity(), is("Hello World!"));
  }

  private int allCounter() {
    try (Http1ClientResponse response = client.get("/api/counter").request()) {
      assertThat(response.status(), is(Status.OK_200));
      JsonNumber number = (JsonNumber) response.as(JsonObject.class).get("all");
      return number.intValue();
    }
  }
}
