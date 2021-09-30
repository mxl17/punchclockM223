package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;

@Path("/entries")
@Authenticated
@Tag(name = "Entries", description = "Handling of entries")
public class EntryController {

    @Inject
    EntryService entryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Entries", description = "Alle Entries abfragen")
    public List<Entry> list() {
        return entryService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Operation(description = "Einzelne Entries mit einer bestimmten ID abfragen")
    public Entry getEntry(@PathParam("id") Long id) {
        return entryService.findEntryByID(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{id}")
    @Operation(description = "Alle Entries holen, die von einem bestimmten user erstellt wurden")
    public List<Entry> getEntryByUserId(@PathParam("id") Long id) {
        return entryService.findEntryByUserID(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Entry", description = "The newly created entry is returned. The id may not be passed.")
    public Entry add(Entry entry) {
        return entryService.createEntry(entry);
    }

    @Path("/{id}")
    @DELETE
    @Operation(summary = "Deletes an Entry", description = "Entry löschen")
    public void delete(@PathParam("id") Long id) {
        entryService.deleteEntry(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "Entry bearbeiten")
    public Entry updateEntry(Entry entry) {
        return entryService.update(entry);
    }
}
