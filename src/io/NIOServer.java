package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NIOServer extends Thread {

    @Override
    public void run() {
        try {
            // 创建seletor和Channel
            Selector selector= Selector.open();
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(),8888));
            serverSocket.configureBlocking(false);
            // 注册到selector,并说明关注点
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务启动：");
            while (true){
                // 阻塞等待就绪的channel，这是关键点之一
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    System.out.println("请求连接");
                    SelectionKey key = iterator.next();
                    // 生产系统中一般会额外进行就绪状态检查
                    sayHelloWorld((ServerSocketChannel) key.channel());
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sayHelloWorld(ServerSocketChannel server) throws IOException {
        SocketChannel client = server.accept();
        client.write(Charset.defaultCharset().encode("Hello NIO"));
        System.out.println("server:hello");
    }

    public static void main(String[] args) {
        NIOServer nioServer=new NIOServer();
        nioServer.start();
        try{
            Socket client = new Socket(InetAddress.getLocalHost(),8888);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println(bufferedReader.readLine());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
