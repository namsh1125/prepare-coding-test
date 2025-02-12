package site.woorifisa.codingtest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SlackResponseDto {

    @JsonProperty("response_type")
    private String responseType;
    private String text;

    public static SlackResponseDto success(String message) {
        return SlackResponseDto.builder()
                .responseType("ephemeral")
                .text("✅ " + message)
                .build();
    }

    public static SlackResponseDto error(String message) {
        return SlackResponseDto.builder()
                .responseType("ephemeral")
                .text("❌ " + message)
                .build();
    }
}
