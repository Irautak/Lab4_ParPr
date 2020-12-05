import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.japi.pf.ReceiveBuilder;

public class TestGroupActor extends AbstractActor {
    private ActorSelection routerActor = getContext().actorSelection("/user/routerActor");

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                            .match(TestGroupMsg.class, msg -> {
                                for (Test test : msg.getTests()) {
                                    routerActor.tell(new TestMsg(msg.getPackageID(), msg.getJsScript(),
                                                msg.getFunctionName(), test), self());
                                }
                            })
                            .build();
    }
}
