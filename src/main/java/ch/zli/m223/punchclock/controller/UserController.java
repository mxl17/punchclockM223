package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.domain.Workspace;
import ch.zli.m223.punchclock.service.UserService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Authenticated
@Tag(name = "Users", description = "Handling of users")
public class UserController {
    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Users", description = "")
    public List<Workspace> list() {
        return userService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public User getUser(@PathParam("id") Long id) {
        return userService.findUserByID(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new User", description = "")
    public User add(User user) {
        return userService.createUser(user);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes a User", description = "")
    public void delete(@PathParam("id") Long id) {
        userService.deleteUser(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User updateUser(User user) {
        return userService.update(user);
    }
}
