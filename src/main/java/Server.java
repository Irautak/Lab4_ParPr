import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.PatternsCS;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;


import java.io.IOException;
import java.util.concurrent.CompletionStage;
import java.util.regex.Pattern;

import static akka.http.javadsl.server.Directives.*;

public class Server {
    private ActorRef storeActor;
    private ActorRef testGroupActor;
    private ActorRef routerActor;

    private static final String SERV_NAME = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("ActSys");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        Server server = new Server(system);

        final Flow<HttpRequest, HttpResponse, NotUsed> flow = server.makeRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                flow,
                ConnectHttp.toHost(SERV_NAME, PORT),
                materializer
        );
        System.out.println("Server have been started");
        System.in.read();
        binding.thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
        System.out.println("Server have been turned off");

    }

    private Server(final ActorSystem system) {
        storeActor = system.actorOf(Props.create(StoreActor.class), "storeActor");
        testGroupActor = system.actorOf(Props.create(TestGroupActor.class), "testGroupActor")
        routerActor = system.actorOf(new RoundRobinPool(8).props(Props.create(TestActor.class)),
                "routerActor");

    }

    private Route makeRoute() {
        return route (
                get(() ->
                            parameter("packageID", (packageID) -> {
                                CompletionStage<Object> result = PatternsCS.ask(storeActor,
                                                                new GetMsg(Integer.parseInt(packageID)),
                                        5000);
                                return completeOKWithFuture(result, Jackson.marshaller());
                            })),
                post(() -> entity(Jackson.unmarshaller(TestGroupMsg.class), msg -> {
                    testGroupActor.tell(msg, ActorRef.noSender());
                    return complete("Test have been started");
                })));
    }
}
