package api.service.resources;

import api.service.datatransferobjects.UserAccountDTO;
import api.service.model.User;
import api.service.model.UserAccount;
import api.service.repository.UserAccountRepo;
import api.service.repository.UserRepo;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/useraccount")

public class UserAccountResources {
    @Context
    private UriInfo uriInfo;

    // this has to be static because the service is stateless:
    private static final UserAccountRepo accountRepo = new UserAccountRepo();
    private static final UserRepo userRepo = new UserRepo();

    @POST //GET at http://localhost:XXXX/customers/3
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(UserAccount account) {
        UserAccountDTO user = accountRepo.getByLogin(account.getUsername(), account.getPassword());
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login credentials were incorrect!").build();
        } else {
            return Response.ok(user).build();
        }
    }

    @DELETE //DELETE at http://localhost:XXXX/customers/3
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id, UserAccount account) {
        UserAccountDTO userAccount = accountRepo.getByLogin(account.getUsername(), account.getPassword());
        if (userAccount == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("The user you are trying to delete does not exist.").build();
        } else {
            if (userAccount.getId() == id) {
                accountRepo.delete(id);
                userRepo.delete(userAccount.getUser().getId());

                // Idempotent method. Always return the same response (even if the resource has already been deleted before).
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Please login with the account you are trying to delete.").build();
            }
        }
    }

    @POST //POST at http://localhost:XXXX/customers/
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserAccount user) {
        if (!accountRepo.add(user)){
            return Response.status(Response.Status.CONFLICT).entity("Username has already been taken.").build();
        } else {
            UserAccountDTO userAccount = new UserAccountDTO(accountRepo.getUserAccount(user.getId()));
            return Response.ok(userAccount).build();
        }
    }

    @PUT //PUT at http://localhost:XXXX/customers/{id}
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response updateUser(@PathParam("id") int id, UserAccount account) {
        boolean result = accountRepo.update(id, account);
        if (!result){
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid user.").build();
        }

        return Response.noContent().build();
    }
}
