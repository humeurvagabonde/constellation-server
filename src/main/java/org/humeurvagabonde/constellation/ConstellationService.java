package org.humeurvagabonde.constellation;

import org.humeurvagabonde.constellation.db.PartyDAO;
import org.humeurvagabonde.constellation.entities.Party;
import org.humeurvagabonde.constellation.resources.PartyResource;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.migrations.MigrationsBundle;

public class ConstellationService extends Service<ConstellationConfiguration>{

    public static void main(String[] args) throws Exception {
        new ConstellationService().run(args);
    }

    private final HibernateBundle<ConstellationConfiguration> hibernateBundle =
        new HibernateBundle<ConstellationConfiguration>(Party.class) {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(ConstellationConfiguration configuration) {
                return configuration.getDatabaseConfiguration();
            }
        };

    @Override
    public void initialize(Bootstrap<ConstellationConfiguration> bootstrap) {
        bootstrap.setName("Constellation - Milky-way");
        bootstrap.addBundle(new MigrationsBundle<ConstellationConfiguration>() {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(ConstellationConfiguration configuration) {
                return configuration.getDatabaseConfiguration();
            }
        });
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(ConstellationConfiguration configuration, Environment environment) throws Exception {
        final PartyDAO dao = new PartyDAO(hibernateBundle.getSessionFactory());

        environment.addResource(new PartyResource(dao));
        // add health checks
    }

}
