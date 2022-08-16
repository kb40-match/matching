package com.matching.kb40.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    private String userId;
    private String mydataId;
    private String job;
    private String mbtiMind;
    private String mbtiRecog;
    private String mbtiJudge;
    private String mbtiTactics;
    private Integer userPoint;
    private String petPrefer;
    private String nickname;
    private String agreeFlag;
    private String hobby;
    private String profileFilename;
    private Integer matchCount;
}
