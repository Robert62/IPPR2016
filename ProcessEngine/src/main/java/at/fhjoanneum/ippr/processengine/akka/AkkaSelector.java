package at.fhjoanneum.ippr.processengine.akka;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.UntypedActorContext;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

public class AkkaSelector {

  private final static Logger LOG = LoggerFactory.getLogger(AkkaSelector.class);

  private static final Timeout TIMEOUT = new Timeout(Duration.create(30, TimeUnit.SECONDS));

  public synchronized Optional<ActorRef> findActor(final UntypedActorContext context,
      final String path) {
    final ActorSelection sel = context.actorSelection(path);

    try {
      final Future<ActorRef> fut = sel.resolveOne(TIMEOUT);
      final ActorRef ref = Await.result(fut, TIMEOUT.duration());
      LOG.debug("Actor [{}] is existing in context [{}], use this actor", path, context.system());
      return Optional.of(ref);
    } catch (final Exception e) {
      LOG.debug("Actor [{}] is not existing or answering in context [{}], therefore create new one",
          path, context.system());
      return Optional.empty();
    }
  }
}
