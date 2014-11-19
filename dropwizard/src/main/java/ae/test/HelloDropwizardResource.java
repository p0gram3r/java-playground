package ae.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class HelloDropwizardResource
{

    @GET
    public HelloDropwizard sayHello()
    {
        return new HelloDropwizard();
    }
}
