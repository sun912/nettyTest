package com.example.nettyServer.handler;

import com.example.nettyServer.model.Frame;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.stream.Stream;

import static io.netty.channel.ChannelHandler.*;

@Slf4j
@RequiredArgsConstructor
@Component
@Sharable
public class DataFrameParsingHandler extends SimpleChannelInboundHandler {

    ByteBuf buff = ByteBufAllocator.DEFAULT.directBuffer(MAX_FRAME_SIZE);
    private Frame frame = new Frame();
    private static final int MAX_FRAME_SIZE = 12;


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("handler Added!");
//        buff = ctx.alloc().buffer(MAX_FRAME_SIZE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        buff = (ByteBuf) msg;
        int readIndex = buff.readerIndex();
        int controlFlag;
//        int temp = buff.readUnsignedShort();
//        log.info("unsigned short message : {}", temp);

        // 프레임 끝까지 읽을 때까지 parsing
        while (buff.isReadable()) {
            frame.setAnchorId(buff.readUnsignedShort());
            frame.setAgentId(buff.readUnsignedShort());
            frame.setSequenceNumber(buff.readUnsignedShort());
            controlFlag = buff.readUnsignedShort();
            frame.setPayloadLength(buff.readUnsignedShort());
            frame.setCheckSum(buff.readUnsignedShort());

            String intToBinary = String.format("%08d", Integer.parseInt(Integer.toBinaryString(controlFlag)));
            frame.setReadyBit(frame.checkFlagBit(intToBinary.charAt(0)));
            frame.setDataBit(frame.checkFlagBit(intToBinary.charAt(1)));
            frame.setRequestBit(frame.checkFlagBit(intToBinary.charAt(2)));
            frame.setFinishBit(frame.checkFlagBit(intToBinary.charAt(4)));
            int modeValue = Integer.parseInt(intToBinary.substring(5, 7));
            frame.setModeType(modeValue);

            // TODO : PayLoad parsing

        }



        /***
         * TODO: Need to test operating parsing well
         */

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
