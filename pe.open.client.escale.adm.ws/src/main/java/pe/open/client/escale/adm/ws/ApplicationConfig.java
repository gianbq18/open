/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.ws;

import java.util.Set;

/**
 *
 * @author IMENDOZA
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(pe.open.client.escale.adm.ws.UsuarioWs.class);
        resources.add(pe.open.client.escale.adm.ws.AgendaMedicaWs.class);
        resources.add(pe.open.client.escale.adm.servlet.SecurityFilter.class);
    }
    
}