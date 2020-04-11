package fr.micronaut.presentation.common.logging;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class LoggingPublisher<T> implements Publisher<T> {

  private final Publisher<T> publisher;

  public LoggingPublisher(Publisher<T> publisher) {
    this.publisher = publisher;
  }

  @Override
  public void subscribe(Subscriber<? super T> actual) {
    publisher.subscribe(new Subscriber<T>() {

      @Override
      public void onSubscribe(Subscription s) {
        actual.onSubscribe(s);
        LoggingPublisher.this.doOnNext();
      }

      @Override
      public void onNext(T t) {
        actual.onNext(t);
      }

      @Override
      public void onError(Throwable t) {
        actual.onError(t);
      }

      @Override
      public void onComplete() {
        actual.onComplete();
      }
    });

  }

  protected void doOnNext(){
    // override purpose
  }
}
