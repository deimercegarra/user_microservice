package com.pragma.user_microservice.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CommonResponseDto <E>{

    private String message;
    private String code;
    private E dto;
    private Long id;
    private Boolean status;

    public CommonResponseDto(Boolean status, String code, String message, E dto, Long id) {
        this.message = message;
        this.dto = dto;
        this.id = id;
        this.status = status;
        this.code = code;
    }

    public CommonResponseDto(Boolean status, String code, String message, E dto) {
        this.message = message;
        this.dto = dto;
        this.status = status;
        this.code = code;
    }

    public CommonResponseDto(String code, String message, Boolean status) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public CommonResponseDto() {
    }

    @Override
    public String toString() {
        return "{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", dto=" + dto +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
