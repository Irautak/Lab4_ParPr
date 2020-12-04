import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;

public class Server {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("ActSys");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        Server server = new Server(system);

    }

    private Server(final ActorSystem system) {
        storeActor = system.actorOf(Props.create(**), "storeActor");
        testActor = system.actorOf(Props.create(**), "testActor")
        routerActor = system.actorOf()
    }
}
