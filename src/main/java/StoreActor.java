import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreActor extends AbstractActor {
    private HashMap<Integer, ArrayList<Test> > store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                            .match(StoreMsg.class, msg -> {
                                if (!store.containsKey(msg.getPackageID())) {
                                    store.put(msg.getPackageID(), msg.getTests());
                                } else {
                                    ArrayList<Test> result = store.get(msg.getPackageID());
                                    result.addAll(msg.getTests());
                                    store.replace(msg.getPackageID(), result);
                                }

                            })
                            .match(GetMsg.class, rq -> {
                                sender().tell(
                                        new StoreMsg(rq.getPackageID(), store.get(rq.getPackageID())),
                                        self()
                                );
                            })
                            .build();
    }
}
