package com.alipay.sofa.example.endpoint.filter;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.util.Set;


/**
 * Add a self define filter to ensure the 8080 port can access the data exposed on 8341 by REST service.
 */
@Provider
@PreMatching
public class CommonContainerResponseFilter extends CorsFilter {

    public void setAllowedOrigins(Set<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

}
