
package pub.ess.esb;

import io.helidon.logging.common.LogConfig;

import io.helidon.config.Config;
// import io.helidon.http.Header;
// import io.helidon.http.HeaderNames;
// import io.helidon.http.HeaderValues;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;
import io.helidon.webserver.staticcontent.StaticContentService;

public class Main {
  // private static final Header UI_REDIRECT =
  // HeaderValues.createCached(HeaderNames.LOCATION, "/ui");

  private Main() {
  }

  public static void main(String[] args) {
    LogConfig.configureRuntime();

    WebServer server = WebServer.builder()
        .config(Config.create().get("server"))
        // .addFeature(StaticContentFeature.builder()
        // .addClasspath(cl -> cl.location("/essapp")
        // .welcome("index.html")
        // .context("/"))
        // // .addContentTypes(
        // // Map.of("html", MediaTypes.APPLICATION_XHTML_XML, "js",
        // // MediaTypes.APPLICATION_JAVASCRIPT))
        // .build())
        .routing(Main::routing)
        // .addRouting(WsRouting.builder().endpoint("/ess/esb/regedit", new
        // WsService()))
        .build()
        .start();

    System.out.println("WEB server is up! http://localhost:" + server.port() + "/");
  }

  /**
   * Updates HTTP Routing.
   */
  static void routing(HttpRouting.Builder routing) {
    routing
        // .any("/", (req, res) -> {
        // // showing the capability to run on any path, and redirecting from root
        // res.status(Status.MOVED_PERMANENTLY_301);
        // res.headers().set(UI_REDIRECT);
        // res.send();
        // })
        .register("/", StaticContentService.builder("/essapp").welcomeFileName("index.html").build())
        .register("/", CounterService::new)
        .register("/greet", new GreetService())
        .get("/simple-greet", (req, res) -> res.send("Hello World!"));
  }
}