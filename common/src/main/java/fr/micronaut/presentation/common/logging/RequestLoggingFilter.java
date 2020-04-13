package fr.micronaut.presentation.common.logging;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;

/**
 * Catch every request and log the body.
 * A blacklist {@link RequestLoggingFilter#EXCLUDE_PATTERN} is available to disable some log pollution.
 * Require the <pre>
 *   request:
 *      log:
 *         enable: true
 * </pre>
 * in the configuration to be active.
 *
 * The {@link RequestLoggingFilter#order} is here to make sure other tracing beans like Brave or
 * Zipkin are executed before the logging.
 */
@Slf4j
@Filter("/**")
public class RequestLoggingFilter implements HttpServerFilter {

  private static final CharSequence APPLIED = RequestLoggingFilter.class.getName() + "-applied";

  @Value("${request.log.enable:`false`}")
  private boolean loggingEnable;

  @Value("${request.log.order:`50`}")
  private int order;

  private static final List<String> EXCLUDE_PATTERN = Collections.singletonList("/health");

  @Override
  public int getOrder(){
    return order;
  }

  private void logRequest(HttpRequest<?> request) {
    String uri = request.getUri().toString();
    if (loggingEnable && !excludeUrl(uri)) {
      StringBuilder sb = new StringBuilder();
      sb.append("METHOD : ");
      sb.append(request.getMethod().toString());
      sb.append("; REQUEST BODY : uri=");
      sb.append(uri);
      sb.append(";");
      Optional<?> requestBody = request.getBody();
      if (requestBody.isPresent()) {
        sb.append("payload=");
        sb.append(requestBody.get().toString());
      }
      log.info(sb.toString());
    }
  }

  private static boolean excludeUrl(String uri) {
    return EXCLUDE_PATTERN.stream().anyMatch(uri::matches);
  }

  @Override
  public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request,
      ServerFilterChain chain) {
    final Publisher<MutableHttpResponse<?>> responsePublisher = chain.proceed(request);
    if (request.getAttribute(APPLIED, Boolean.class).isPresent()){
      return responsePublisher;
    } else {
      request.setAttribute(APPLIED, true);
      return new LoggingPublisher(responsePublisher){
        @Override
        protected void doOnNext(){
          logRequest(request);
        }
      };
    }

  }
}
