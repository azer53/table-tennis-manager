package com.eli.integration.frenoy;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import src.main.java.com.eli.integration.GetMembersRequest;
import src.main.java.com.eli.integration.GetMembersResponse;

import java.math.BigInteger;


/**
 * Created by colpaertel on 25/09/2017.
 */
public class FrenoyPlayers extends WebServiceGatewaySupport {

    public static final String SINT_NIKLAAS = "OVL-039";

    public GetMembersResponse getAllSintNiklaasMembers() {
        GetMembersRequest request = new GetMembersRequest();
        request.setClub(SINT_NIKLAAS);
        return (GetMembersResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public GetMembersResponse getMembers(String clubCode) {
        GetMembersRequest request = new GetMembersRequest();
        request.setClub(clubCode);
        return (GetMembersResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public GetMembersResponse getMember(BigInteger frenoyId) {
        GetMembersRequest request = new GetMembersRequest();
        request.setUniqueIndex(frenoyId);
        return (GetMembersResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
