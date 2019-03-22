package fr.ulille.iut.chatisfait;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ReceiverClient {

    void getJsonArrayResponse(JSONArray response);

    void postJsonObjectResponse(JSONObject response);
}
