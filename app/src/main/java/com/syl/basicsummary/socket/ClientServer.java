package com.syl.basicsummary.socket;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by j3767 on 2017/3/4.
 *
 * @Describe
 * @Called
 */

public class ClientServer implements Runnable {
    private ServerSocket mServerSocket;
    private Context mContext;
    private int mPort;
    private boolean mIsRunning;

    public ClientServer(Context context, int port) {
        mContext = context;
        mPort = port;
    }

    public void start() {
        mIsRunning = true;
        new Thread(this).start();
    }

    public void stop() {
        mIsRunning = false;
        if (mServerSocket != null) {
            try {
                mServerSocket.close();
                mServerSocket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {
            mServerSocket = new ServerSocket(mPort);
            while (mIsRunning) {
                if (null != mServerSocket) {
                    Socket socket = mServerSocket.accept();
                    handle(socket);
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle(Socket socket) {
        BufferedReader reader = null;
        PrintStream outPut = null;
        try {
            AssetManager assetManager = mContext.getResources().getAssets();
            String route = "serviceDemo.htm";
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outPut = new PrintStream(socket.getOutputStream());
            byte[] bytes;
            bytes = loadData(route, assetManager);
            outPut.write(bytes);
            outPut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != outPut) {
                    outPut.close();
                }
                if (null != reader) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] loadData(String route, AssetManager assetManager) {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            inputStream = assetManager.open(route);
            byte[] buffer = new byte[1024 * 8];
            int size = inputStream.read(buffer);
            while (size > 0) {
                outputStream.write(buffer, 0, size);
            }
            outputStream.flush();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
