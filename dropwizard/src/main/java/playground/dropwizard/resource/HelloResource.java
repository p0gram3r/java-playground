package playground.dropwizard.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.util.concurrent.atomic.AtomicLong;

import com.codahale.metrics.annotation.Timed;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import playground.dropwizard.api.Saying;

@Api(value = "/hello", description = "Operations about greetings")
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    @ApiOperation(
        value = "greets someone by name",
        notes = "no blabla here",
        response = Saying.class
    )
    public Saying sayHello(@QueryParam("name") String name) {
        final String s = name == null ? defaultName : name;
        final String value = String.format(template, s);
        return new Saying(counter.incrementAndGet(), value);
    }
}
