package services;

import akka.actor.UntypedActor;
import play.Logger;

/**
 * Created by hannan on 2/2/15.
 */
public class ScheduledService extends UntypedActor{

    @Override
    public void onReceive(Object o) throws Exception {
        Logger.debug("activity of service started");
    }
}
