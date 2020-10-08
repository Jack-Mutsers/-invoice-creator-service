package api.service.resources;

import api.service.datatransferobjects.ProductDTO;
import api.service.model.Product;
import api.service.repository.ProductRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/products")

public class ProductResources {
    @Context
    private UriInfo uriInfo;

    // this has to be static because the service is stateless:
    private static final ProductRepo repo = new ProductRepo();

    @GET //GET at http://localhost:XXXX/customers/3
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") int id) {
        ProductDTO product = new ProductDTO(repo.getProduct(id));
        if (product == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid product identifier.").build();
        } else {
            return Response.ok(product).build();
        }
    }

    @GET //GET at http://localhost:XXXX/customers
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        ProductDTO Dto = new ProductDTO();
        List<ProductDTO> products = Dto.getProductList(repo.getProducts());
        GenericEntity<List<ProductDTO>> entity = new GenericEntity<>(products) {  };
        return Response.ok(entity).build();
    }

    @DELETE //DELETE at http://localhost:XXXX/customers/3
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") int id) {
        repo.delete(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return Response.noContent().build();
    }

    @POST //POST at http://localhost:XXXX/customers/
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        if (!repo.add(product)){
            String entity =  "Customer with product id " + product.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + product.getId(); // url of the created student
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    @PUT //PUT at http://localhost:XXXX/customers/{id}
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response updateProduct(@PathParam("id") int id, Product product) {
        boolean result = repo.update(id, product);
        if (!result){
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid product.").build();
        }

        return Response.noContent().build();
    }
}
