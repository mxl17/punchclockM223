package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Workspace;
import ch.zli.m223.punchclock.service.WorkspaceService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/workspaces")
@Authenticated
@Tag(name = "Workspaces", description = "Handling of workspaces")
public class WorkspaceController {
    @Inject
    WorkspaceService workspaceService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Workspaces", description = "Alle Workspaces abfragen")
    public List<Workspace> list() {
        return workspaceService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Operation(description = "Einzelne Workspaces mit einer bestimmten ID abfragen")
    public Workspace getWorkspace(@PathParam("id") Long id) {
        return workspaceService.findWorkspaceByID(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Workspace", description = "The newly created workspace is returned. The id may not be passed.")
    public Workspace add(Workspace workspace) {
        return workspaceService.createWorkspace(workspace);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes a Workspace", description = "Ein Workspace löschen")
    public void delete(@PathParam("id") Long id) {
        workspaceService.deleteWorkspace(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "Ein Workspace bearbeiten")
    public Workspace updateWorkspace(Workspace workspace) {
        return workspaceService.update(workspace);
    }
}
