package com.matching.kb40.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatDto {
    private Long chatId;           
    private String userId;
    private String content;
    private String createdDate;
}
