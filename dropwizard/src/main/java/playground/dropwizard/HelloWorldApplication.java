package playground.dropwizard;

import com.google.common.collect.ImmutableMap;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.commons.lang3.text.StrSubstitutor;
import playground.dropwizard.config.HelloWorldConfiguration;
import playground.dropwizard.health.TemplateHealthCheck;
import playground.dropwizard.resource.HelloWorldResource;

import java.util.Map;

import static com.google.common.collect.Maps.fromProperties;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
            new ResourceConfigurationSourceProvider(),
            new StrSubstitutor(resolveEnvironmentProperties())
        ));
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
            configuration.template,
            configuration.defaultName
        );
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.template);

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

    private Map<String, String> resolveEnvironmentProperties() {
        return ImmutableMap.<String, String>builder()
            .putAll(System.getenv())
            .putAll(fromProperties(System.getProperties()))
            .build();
    }
}
