package com.goBoard.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.goBoard.shared.Player;

/**
 * Created by Renaud Laine on 14-12-03.
 */
@RemoteServiceRelativePath("getPlayers")
public interface PlayerInfoService extends RemoteService {
    Player[] getPlayers();
}
