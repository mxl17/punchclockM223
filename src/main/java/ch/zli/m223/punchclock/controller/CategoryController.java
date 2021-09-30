package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.service.CategoryService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categories")
@Authenticated
@Tag(name = "Categories", description = "Handling of categories")
public class CategoryController {
    @Inject
    CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Categories", description = "Alle Kategorien abfragen")
    public List<Category> list() {
        return categoryService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Operation(description = "Einzelne Kategorien mit einer bestimmten ID abfragen")
    public Category getCategory(@PathParam("id") Long id) {
        return categoryService.findCategoryByID(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Category", description = "The newly created category is returned. The id may not be passed.")
    public Category add(Category category) {
        return categoryService.createCategory(category);
    }

    @Path("/{id}")
    @DELETE
    @Operation(summary = "Deletes a Category", description = "Eine einzelne Kategorie nach ID löschen")
    public void delete(@PathParam("id") Long id) {
        categoryService.deleteCategory(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "Kategorien bearbeiten")
    public Category updateCategory(Category category) {
        return categoryService.update(category);
    }
}
