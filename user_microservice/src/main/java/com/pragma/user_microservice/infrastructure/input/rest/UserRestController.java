package com.pragma.user_microservice.infrastructure.input.rest;

import com.pragma.user_microservice.application.dto.request.CustomerRequestDto;
import com.pragma.user_microservice.application.dto.request.EmployeeRequestDto;
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
            @ApiResponse(responseCode = "201", description = "User created.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized User.", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists.", content = @Content)
    })
    @PostMapping("/owner")
    public ResponseEntity<Map<String, String>> saveOwner(@Valid @RequestBody UserRequestDto userRequestDto) {
        iUserHandler.saveUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("201", "CREATED"));
    }

    @Operation(summary = "Add a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized User.", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists.", content = @Content)
    })
    @PostMapping("/customer")
    public ResponseEntity<Map<String, String>> saveCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        iUserHandler.saveCustomer(customerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("201", "CREATED"));
    }

    @Operation(summary = "Add a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized User.", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists.", content = @Content)
    })
    @PostMapping("/employee")
    public ResponseEntity<Map<String, String>> saveEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        iUserHandler.saveEmployee(employeeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("201", "CREATED"));
    }


    @Operation(summary = "Find role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful request.", content = @Content),
            @ApiResponse(responseCode = "404", description = "No data found for the requested petition.", content = @Content)
    })
    @GetMapping("/findRole/{userId}")
    public ResponseEntity<CommonResponseDto> findRole(@PathVariable Long userId) {
        CommonResponseDto commonResponseDto = iUserHandler.findRole(userId);

        if (!commonResponseDto.getStatus())
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(commonResponseDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponseDto);
    }

    @Operation(summary = "Find user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful request.", content = @Content),
            @ApiResponse(responseCode = "404", description = "No data found for the requested petition.", content = @Content)
    })
    @GetMapping("/findUser/{userId}")
    public ResponseEntity<CommonResponseDto> findUser(@PathVariable Long userId) {
        CommonResponseDto commonResponseDto = iUserHandler.getUser(userId);

        if (!commonResponseDto.getStatus())
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(commonResponseDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponseDto);
    }

    @Operation(summary = "Find user by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful request.", content = @Content),
            @ApiResponse(responseCode = "404", description = "No data found for the requested petition.", content = @Content)
    })
    @GetMapping("/findUserByUsername/{username}")
    public ResponseEntity<CommonResponseDto> findUserByUsername(@PathVariable String username) {
        CommonResponseDto commonResponseDto = iUserHandler.getUserByUsername(username);

        if (!commonResponseDto.getStatus())
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(commonResponseDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponseDto);
    }

}