package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.AuthenticationService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.persistence.Table;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/auth")
@Tag(name = "Auth", description = "Authentication of Users")
public class AuthenticationController {

    @Inject
    AuthenticationService authenticationService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(User user) {
        // TODO check if user exists
        if(authenticationService.checkIfUserExists(user)) {
            return authenticationService.GenerateValidJwtToken(user.getUsername());
        }
        else {
            throw new NotAuthorizedException("User ["+user.getUsername()+"] not known");
        }
    }
}