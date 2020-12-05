import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.japi.pf.ReceiveBuilder;

public class TestGroupActor extends AbstractActor {
    private ActorSelection routerActor = getContext().actorSelection("/user/routerActor");

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                            .match()
    }
}
