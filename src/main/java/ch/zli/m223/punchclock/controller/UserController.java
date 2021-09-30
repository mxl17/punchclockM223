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
import java.util.Objects;

@Path("/users")
@Tag(name = "Users", description = "Handling of users")
public class UserController {
    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Users", description = "Alle Users abfragen")
    public List<User> list() {
        return userService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Operation(description = "Einzelne User mit einer bestimmten ID abfragen")
    public User getUser(@PathParam("id") Long id) {
        return userService.findUserByID(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new User", description = "Einen User erfassen")
    public boolean add(User newUser) {
        boolean check = true;
        for (User user : list()) {
            if (Objects.equals(user.getUsername(), newUser.getUsername())) {
                check = false;
                break;
            }
        }
        if (check) {
            userService.createUser(newUser);
            return true;
        } else {
            return false;
        }
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes a User", description = "Einen User löschen")
    public void delete(@PathParam("id") Long id) {
        userService.deleteUser(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "Einen User bearbeiten")
    public User updateUser(User user) {
        return userService.update(user);
    }
}
