package api.service.resources;

import api.service.model.ProductCategory;
import api.service.repository.ProductCategoryRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/productcategory")

public class ProductCategoryResources {
    @Context
    private UriInfo uriInfo;

    // this has to be static because the service is stateless:
    private static final ProductCategoryRepo repo = new ProductCategoryRepo();

    @GET //GET at http://localhost:XXXX/productcategory/3
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory(@PathParam("id") int id) {
        ProductCategory category = repo.getCategory(id);
        if (category == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid category identifier.").build();
        } else {
            return Response.ok(category).build();
        }
    }

    @GET //GET at http://localhost:XXXX/productcategory
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategory() {
        List<ProductCategory> categories = repo.getCategories();
        GenericEntity<List<ProductCategory>> entity = new GenericEntity<>(categories) {  };
        return Response.ok(entity).build();
    }

    @DELETE //DELETE at http://localhost:XXXX/productcategory/3
    @Path("{id}")
    public Response deleteCategory(@PathParam("id") int id) {
        repo.delete(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return Response.noContent().build();
    }

    @POST //POST at http://localhost:XXXX/productcategory/
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCategory(ProductCategory category) {
        if (!repo.add(category)){
            String entity =  "Customer with productCategory id " + category.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + category.getId(); // url of the created student
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    @PUT //PUT at http://localhost:XXXX/productcategory/{id}
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response updateCategory(@PathParam("id") int id, ProductCategory category) {
        boolean result = repo.update(id, category);
        if (!result){
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid ProductCategory.").build();
        }

        return Response.noContent().build();
    }
}
