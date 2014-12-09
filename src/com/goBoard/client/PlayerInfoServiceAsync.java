package com.goBoard.client;

import com.goBoard.shared.Player;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by Renaud Laine on 14-12-03.
 */
public interface PlayerInfoServiceAsync {
    void getPlayers(AsyncCallback<Player[]> callback);
}
