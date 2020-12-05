import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.net.http.HttpRequest;

public class Server {
    private ActorRef storeActor;
    private ActorRef testGroupActor;
    private ActorRef routerActor;

    private static final String SERV_NAME = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("ActSys");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        Server server = new Server(system);

        final Flow<HttpRequest, HttpResponse, NotUsed> flow =

    }

    private Server(final ActorSystem system) {
        storeActor = system.actorOf(Props.create(StoreActor.class), "storeActor");
        testGroupActor = system.actorOf(Props.create(TestGroupActor.class), "testGroupActor")
        routerActor = system.actorOf(new RoundRobinPool(8).props(Props.create(TestActor.class)),
                "routerActor");

    }

    private Route makeRoute() {
        return route (
                
        )
    }
}
