package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DemoServer extends Thread{

    private ServerSocket serverSocket;

    private int getPort(){
        return serverSocket.getLocalPort();
    }

    @Override
    public void run() {
        try {
            serverSocket=new ServerSocket(0);
            while (true){
                Socket accept = serverSocket.accept();
                ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(2,5,2, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(3), new ThreadPoolExecutor.CallerRunsPolicy());
                threadPoolExecutor.execute(()->{
                    try (PrintWriter out = new PrintWriter(accept.getOutputStream())) {
                        out.println("Hello world!");
                        out.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        DemoServer server=new DemoServer();
        server.start();
        try{
            Socket client = new Socket(InetAddress.getLocalHost(), server.getPort());
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(client.getInputStream()));
            bufferedReader.lines().forEach(s->{
                System.out.println(s);
            });
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
