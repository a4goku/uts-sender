package uts.sender.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    private static class SingletonHolder{
        static final NettyClient instance = new NettyClient();
    }

    public static NettyClient getInstance(){
        return SingletonHolder.instance;
    }

    private String host = "127.0.0.1";

    private int port = 8765;

    private EventLoopGroup group;

    private ChannelFuture cf;

    private Bootstrap b;
    private NettyClient(){
        group = new NioEventLoopGroup();
        b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        sc.pipeline().addLast(new NettyClientHandler());
                    }
                });
    }

    public void connect(){
        try {
            this.cf = b.connect(host, port).sync();
            System.err.println("远程服务器端已经连接。。 可以进行数据通信");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            this.cf.channel().closeFuture().sync();
            this.group.shutdownGracefully();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public ChannelFuture getChannelFuture(){
        if(this.cf == null){
            this.connect();
        }
        if(!this.cf.channel().isActive()){
            this.connect();
        }
        return this.cf;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
