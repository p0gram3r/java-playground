package playground.dropwizard.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class HelloWorldConfiguration extends Configuration {

    @JsonProperty
    @NotEmpty
    public String template;

    @JsonProperty
    @NotEmpty
    public String defaultName = "Stranger";

    @JsonProperty
    @NotEmpty
    public String foo;

}