package playground.dropwizard;

import static com.google.common.collect.Maps.fromProperties;

import java.util.Map;

import org.apache.commons.text.StrSubstitutor;

import com.google.common.collect.ImmutableMap;

import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import playground.dropwizard.config.ServiceConfiguration;
import playground.dropwizard.health.TemplateHealthCheck;
import playground.dropwizard.resource.HelloResource;

public class HelloWorldApplication extends Application<ServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-dropwizard";
    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        // read System Properties and ENV variables
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
            new ResourceConfigurationSourceProvider(),
            new StrSubstitutor(resolveEnvironmentProperties())
        ));

        // add bundle for swagger UI
        bootstrap.addBundle(new SwaggerBundle<ServiceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(ServiceConfiguration cfg) {
                return cfg.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(ServiceConfiguration configuration, Environment environment) {
        final HelloResource helloResource = new HelloResource(
            configuration.template,
            configuration.defaultName
        );

        environment.jersey().register(helloResource);

        environment.healthChecks().register("template", new TemplateHealthCheck(configuration.template));
    }

    private Map<String, String> resolveEnvironmentProperties() {
        return ImmutableMap.<String, String>builder()
            .putAll(System.getenv())
            .putAll(fromProperties(System.getProperties()))
            .build();
    }

}
