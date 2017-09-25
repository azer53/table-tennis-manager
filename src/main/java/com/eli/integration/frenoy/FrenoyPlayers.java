package com.eli.integration.frenoy;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import src.main.java.com.eli.integration.GetMembersRequest;
import src.main.java.com.eli.integration.GetMembersResponse;


/**
 * Created by colpaertel on 25/09/2017.
 */
public class FrenoyPlayers extends WebServiceGatewaySupport {

    public GetMembersResponse getAllSintNiklaasMembers() {
        GetMembersRequest request = new GetMembersRequest();
        request.setClub("OVL-039");
        return (GetMembersResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
