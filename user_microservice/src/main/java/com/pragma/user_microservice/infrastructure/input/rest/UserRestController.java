package com.pragma.user_microservice.infrastructure.input.rest;

import com.pragma.user_microservice.application.dto.request.UserRequestDto;
import com.pragma.user_microservice.application.dto.response.CommonResponseDto;
import com.pragma.user_microservice.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler iUserHandler;

    @Operation(summary = "Add a new owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "409", description = "User already exists",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @PostMapping("/owner")
    public ResponseEntity<Map<String, String>> saveOwner(@Valid @RequestBody UserRequestDto userRequestDto) {
        iUserHandler.saveUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("201", "CREATED"));
    }


    @Operation(summary = "Find owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful request",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @GetMapping("/findOwner/{userId}")
    public ResponseEntity<CommonResponseDto> findOwner(@PathVariable Long userId) {
        CommonResponseDto commonResponseDto = iUserHandler.findOwner(userId);

        if (!commonResponseDto.getStatus())
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(commonResponseDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponseDto);
    }

}