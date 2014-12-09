package com.goBoard.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.goBoard.client.PlayerInfoService;
import com.goBoard.shared.Player;

/**
 * Created by Renaud Laine on 14-12-03.
 */
public class PlayerInfoServiceImpl extends RemoteServiceServlet implements PlayerInfoService {

    public Player[] getPlayers() {

        //TODO: Replace test players by actual players from the database.

        Player[] testPlayers = new Player[10];
        for (int i = 0; i < testPlayers.length; i++) {
            testPlayers[i] = new Player("Joe" + i);
        }

        return testPlayers;
    }
}