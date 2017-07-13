package playground.dropwizard;

import com.google.common.collect.ImmutableMap;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.apache.commons.lang3.text.StrSubstitutor;
import playground.dropwizard.config.ServiceConfiguration;
import playground.dropwizard.health.TemplateHealthCheck;
import playground.dropwizard.resource.HelloResource;

import java.util.Map;

import static com.google.common.collect.Maps.fromProperties;

public class HelloWorldApplication extends Application<ServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
            new ResourceConfigurationSourceProvider(),
            new StrSubstitutor(resolveEnvironmentProperties())
        ));

        initSwaggerBundle(bootstrap);
    }


    @Override
    public void run(ServiceConfiguration configuration, Environment environment) {
        final HelloResource resource = new HelloResource(
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

    private void initSwaggerBundle(Bootstrap<ServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<ServiceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(ServiceConfiguration cfg) {
                return cfg.swaggerBundleConfiguration;
            }
        });
    }
}
