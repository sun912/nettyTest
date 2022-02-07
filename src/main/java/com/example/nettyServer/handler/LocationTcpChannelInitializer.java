package com.example.nettyServer.handler;

import com.example.nettyServer.decoder.FrameDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class LocationTcpChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final DataFrameParsingHandler dataFrameParsingHandler;
    private static final int MAX_FRAME_SIZE = 14;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline
//               .addLast(new LoggingHandler(LogLevel.INFO))
                .addLast("FrameDecoder", new FrameDecoder())
//                .addLast("ParsingDecoder",new ParsingDecoder())
                .addLast("DataFrameParsingHandler", dataFrameParsingHandler);
                //.addLast(positioningHandler)  TODO: Positioning algorithm handler 추가필요


    }
}
