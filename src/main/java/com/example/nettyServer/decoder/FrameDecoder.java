package com.example.nettyServer.decoder;

import com.example.nettyServer.config.NettyProperties;
import com.example.nettyServer.model.Frame;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class FrameDecoder extends ByteToMessageDecoder {
    /***
     * [Frame Format : 24 bytes]
     * [Payload Format : 3+1451,i.e, 1454 bytes]
     * [Total Data Format : 1478 bytes]
     */

    private static final int MAX_FRAME_SIZE = 12;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < MAX_FRAME_SIZE) {
            return;
        }

//        log.info("readable bytes: {}", in.readableBytes());
        out.add(in.readBytes(MAX_FRAME_SIZE));
//        log.info("readable bytes: {}", in.readableBytes());
    }

}
