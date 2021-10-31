package com.example.client_chat.Utils;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketIO {
    public static Socket mSocket;
    static {
        try {
            mSocket = IO.socket("http://192.168.1.14:6000");
        } catch (URISyntaxException ignored) {
        }
    }
}
