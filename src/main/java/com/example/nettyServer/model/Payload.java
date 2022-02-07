package com.example.nettyServer.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Payload {
    private int opcode;
    private int command;

}
