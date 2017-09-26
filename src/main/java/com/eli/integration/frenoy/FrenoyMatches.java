package com.eli.integration.frenoy;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import src.main.java.com.eli.integration.GetMatchesRequest;
import src.main.java.com.eli.integration.GetMatchesResponse;


/**
 * Created by colpaertel on 25/09/2017.
 */
public class FrenoyMatches extends WebServiceGatewaySupport {

    public static final String SINT_NIKLAAS = "OVL-039";


    public GetMatchesResponse getAllSintNiklaasMatches(int playWeek) {
        GetMatchesRequest request = new GetMatchesRequest();
        request.setClub(SINT_NIKLAAS);
        request.setWeekName(String.valueOf(playWeek));
        request.setWithDetails(true);
        return (GetMatchesResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
