package org.humeurvagabonde.constellation.db;

import java.util.List;

import org.hibernate.SessionFactory;
import org.humeurvagabonde.constellation.entities.Party;

import com.google.common.base.Optional;
import com.yammer.dropwizard.hibernate.AbstractDAO;

public class PartyDAO extends AbstractDAO<Party> {

    public PartyDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Party> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Party create(Party person) {
        return persist(person);
    }

    public List<Party> findAll() {
        return list(namedQuery("Party.findAll"));
    }

}
