import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.japi.pf.ReceiveBuilder;

public class TestActor extends AbstractActor {

    private ActorSelection storeActor = getContext().actorSelection("/user/storeActor");

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                            .match()
    }


}
