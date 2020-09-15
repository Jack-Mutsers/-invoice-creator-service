package api.service.resources;

import api.service.model.User;
import api.service.repository.UserRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/user")

public class UserResources {
    @Context
    private UriInfo uriInfo;

    // this has to be static because the service is stateless:
    private static final UserRepo repo = new UserRepo();

    @GET //GET at http://localhost:XXXX/customers/3
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        User user = repo.getUser(id);
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid user identifier.").build();
        } else {
            return Response.ok(user).build();
        }
    }

    @GET //GET at http://localhost:XXXX/customers
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUser() {
        List<User> customers = repo.getUsers();
        GenericEntity<List<User>> entity = new GenericEntity<>(customers) {  };
        return Response.ok(entity).build();
    }

    @DELETE //DELETE at http://localhost:XXXX/customers/3
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id) {
        repo.delete(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return Response.noContent().build();
    }

    @POST //POST at http://localhost:XXXX/customers/
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        if (!repo.add(user)){
            String entity =  "Customer with user id " + user.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + user.getId(); // url of the created student
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    @PUT //PUT at http://localhost:XXXX/customers/{id}
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response updateUser(@PathParam("id") int id, User user) {
        boolean result = repo.update(id, user);
        if (!result){
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid user.").build();
        }

        return Response.noContent().build();
    }
}
