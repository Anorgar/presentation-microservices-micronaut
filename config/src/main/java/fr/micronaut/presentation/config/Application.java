package applications.microservices.support.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

@SpringBootApplication
@EnableConfigServer
@SuppressWarnings("{squid:S2095,common-java:InsufficientBranchCoverage}")
public class Application implements CommandLineRunner, ApplicationListener {

    private static final String LOG_PREFIX = "SUPPORT::Config - ";
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    /**
     * Catch service started and push event to Analytics App
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        LOG.info("{}StringBoot version:[{}]", LOG_PREFIX, org.springframework.boot.SpringBootVersion.getVersion());
        LOG.info("{}onApplicationEvent [Service Started]", LOG_PREFIX);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextClosedEvent) {
            LOG.warn(LOG_PREFIX + "onApplicationEvent [Service Stopped]");
        }
    }
}
