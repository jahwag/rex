package com.github.jahwag.rex.cdi.service;

import com.github.jahwag.rex.cdi.App;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.runner.RunWith;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

@RunWith(Arquillian.class)
public abstract class AbstractIT {

    @Deployment
    public static Archive createDeployment() throws Exception {
        return ShrinkWrap.create(JAXRSArchive.class, "cdi.war")
                         .addPackages(true, App.class.getPackage())
                         .addAsResource("project-defaults.yml")
                         .addAsResource("META-INF/load.sql")
                         .addAsResource("META-INF/persistence.xml")
                         .addAllDependencies();
    }

}
