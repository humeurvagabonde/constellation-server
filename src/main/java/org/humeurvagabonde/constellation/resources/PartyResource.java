package org.humeurvagabonde.constellation.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.humeurvagabonde.constellation.db.PartyDAO;
import org.humeurvagabonde.constellation.entities.Party;

import com.yammer.dropwizard.hibernate.UnitOfWork;

@Path("/party/{partyId}")
@Produces(MediaType.APPLICATION_JSON)
public class PartyResource {

    private final PartyDAO partyDAO;

    public PartyResource(PartyDAO partyDAO) {
        this.partyDAO = partyDAO;
    }

    @GET
    @UnitOfWork
    public List<Party> listParties() {
        return partyDAO.findAll();
    }

}
