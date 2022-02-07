package com.example.nettyServer.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.example.nettyServer.model.ModeStatus.*;
import static com.example.nettyServer.model.ModeStatus.URGENT;

@Slf4j
@Component
@RequiredArgsConstructor
@Getter
@Setter
public class Frame {
    private int anchorId;
    private int agentId;
    private int sequenceNumber;
    private int payloadLength;
    private int checkSum;

    private boolean readyBit;
    private boolean dataBit;
    private boolean requestBit;
    private boolean finishBit;
    private byte modeBit;
    private ModeStatus modeType;

    public boolean checkFlagBit(char bit){
        if (bit == '1') {
            return true;
        }else{
            return false;
        }
    }

    public ModeStatus setModeType(int val) {

        switch(val){
            case 1:
                return COMMON;

            case 2:
                return URGENT;

            case 3:
                return STATUS_INFORMATION;

            case 4:
                return SW_UPDATE;

            default:
                log.info("Unknown mode!");
                return COMMON;
        }
    }




}
