package com.goBoard.client;

import com.goBoard.shared.Player;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class GoBoard implements EntryPoint {

    private VerticalPanel _vPanel = new VerticalPanel();
    private Label _lblMessage = new Label("Please choose a player name.");
    private FlexTable _playerTable = new FlexTable();
    private TextBox _txtPlayerName = new TextBox();
    private Button _btnRegister = new Button("Register");
    private Button _btnPlay = new Button("Play");

    private PlayerInfoServiceAsync playerInfoSvc = GWT.create(PlayerInfoService.class);

    public void onModuleLoad() {

        _playerTable.setText(0, 0, "Name");
        _playerTable.setText(0, 1, "Score");
        _playerTable.setText(0, 2, "Status");
        _playerTable.setText(0, 3, "Invite to game");

        _vPanel.add(_lblMessage);
        _vPanel.add(_txtPlayerName);
        _vPanel.add(_btnRegister);
        _vPanel.add(_playerTable);
        _vPanel.add(_btnPlay);

        RootPanel.get().add(_vPanel);

        _btnPlay.setVisible(false);
        _txtPlayerName.setFocus(true);

        _btnRegister.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                registerPlayer();
            }
        });

        Timer t = new Timer() {
            @Override
            public void run() {
                refreshPlayerList();
            }
        };

        t.schedule(5000);
    }

    private void setErrorMessage(String errorMsg) {
        _lblMessage.setStyleName("errorMessage");
        _lblMessage.setText(errorMsg);
    }

    private void setMessage(String message) {
        _lblMessage.removeStyleName("errorMessage");
        _lblMessage.setText(message);
    }

    private void registerPlayer() {

        //TODO: Add logic to allow the new player to be registered server-side.

        Player player = new Player(_txtPlayerName.getText());

        if (!player.name.matches("^[0-9A-Za-z\\.]{1,10}$")) {
            setErrorMessage("'" + player.name + "' is not valid.");
            return;
        }

        setMessage("You are now registered as '" + player.name + "'");
        _btnRegister.setVisible(false);
        _btnPlay.setVisible(true);
        _txtPlayerName.setText("");
    }

    private void updateTable(Player[] players) {

        int numRows = _playerTable.getRowCount();

        for (int i = 0; i < players.length; i++) {
            _playerTable.setText(i + numRows, 0, players[i].name);
            _playerTable.setText(i + numRows, 1, String.valueOf(players[i].score));
            _playerTable.setText(i + numRows, 2, String.valueOf(players[i].status));
        }
    }

    private void refreshPlayerList() {

        if (playerInfoSvc == null) {
            playerInfoSvc = GWT.create(PlayerInfoService.class);
        }

        AsyncCallback<Player[]> callback = new AsyncCallback<Player[]>() {
            @Override
            public void onFailure(Throwable caught) {
                _lblMessage.setText("Enable to update players list.");
            }

            @Override
            public void onSuccess(Player[] result) {
                updateTable(result);
            }
        };

        playerInfoSvc.getPlayers(callback);
    }
}
