/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.example.endpoint.facade;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.alipay.sofa.example.endpoint.constants.RestConstants;
import com.alipay.sofa.example.endpoint.constants.URLConstants;

@Path(URLConstants.REST_API_PEFFIX + "/mq")
@Consumes(RestConstants.DEFAULT_CONTENT_TYPE)
@Produces(RestConstants.DEFAULT_CONTENT_TYPE)
public interface SendMessageFacade {
    /**
     * For example: http://localhost:8341/webapi/mq/send
     * @return msgId
     */
    @GET
    @Path("/send")
    String send();
}