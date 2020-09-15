package api.service.resources;

import api.service.model.Customer;
import api.service.repository.CustomerRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/customers")
public class CustomerResources {

    @Context
    private UriInfo uriInfo;

    // this has to be static because the service is stateless:
    private static final CustomerRepo repo = new CustomerRepo();

    @GET //GET at http://localhost:XXXX/customers/3
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("id") int id) {
        Customer customer = repo.getCustomer(id);
        if (customer == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid customer identifier.").build();
        } else {
            return Response.ok(customer).build();
        }
    }

    @GET //GET at http://localhost:XXXX/customers
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        List<Customer> customers = repo.getCustomers();
        GenericEntity<List<Customer>> entity = new GenericEntity<>(customers) {  };
        return Response.ok(entity).build();
    }

    @DELETE //DELETE at http://localhost:XXXX/customers/3
    @Path("{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        repo.delete(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return Response.noContent().build();
    }

    @POST //POST at http://localhost:XXXX/customers/
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        if (!repo.add(customer)){
            String entity =  "Customer with customer id " + customer.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + customer.getId(); // url of the created student
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    @PUT //PUT at http://localhost:XXXX/customers/{id}
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response updateCustomer(@PathParam("id") int id, Customer customer) {
        boolean result = repo.update(id, customer);
        if (!result){
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid Customer.").build();
        }

        return Response.noContent().build();
    }
}
