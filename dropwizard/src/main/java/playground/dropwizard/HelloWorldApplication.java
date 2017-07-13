package playground.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import playground.dropwizard.config.HelloWorldConfiguration;
import playground.dropwizard.health.TemplateHealthCheck;
import playground.dropwizard.resource.HelloWorldResource;

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
        // TODO optimize
        bootstrap.setConfigurationSourceProvider(p -> getClass().getClassLoader().getResourceAsStream(p));

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

}