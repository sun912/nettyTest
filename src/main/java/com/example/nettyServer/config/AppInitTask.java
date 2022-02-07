package com.example.nettyServer.config;

import com.example.nettyServer.socket.NettySocketServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AppInitTask implements ApplicationListener<ApplicationReadyEvent> {

    private final NettySocketServer nettySocketServer;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        nettySocketServer.start();
    }
}
