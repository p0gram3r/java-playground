package playground.dropwizard.config;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class ServiceConfiguration extends Configuration {

    @JsonProperty
    @NotEmpty
    public String template;

    @JsonProperty
    @NotEmpty
    public String defaultName = "Stranger";

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;
}