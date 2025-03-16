package br.com.alysonrodrigo.elevechangecambio.interfaces.controller;

import br.com.alysonrodrigo.elevechangecambio.application.dto.CurrencyConversionDTO;
import br.com.alysonrodrigo.elevechangecambio.application.usecase.ConvertCurrencyUseCase;
import br.com.alysonrodrigo.elevechangecambio.application.usecase.GetAllConvertCurrencyUseCase;
import br.com.alysonrodrigo.elevechangecambio.interfaces.mapper.CurrencyConversionMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conversion")
@Slf4j
public class CurrencyConversionController {

    private final ConvertCurrencyUseCase convertCurrencyUseCase;
    private final GetAllConvertCurrencyUseCase getAllConvertCurrencyUseCase;

    public CurrencyConversionController(ConvertCurrencyUseCase convertCurrencyUseCase,
                                        GetAllConvertCurrencyUseCase getAllConvertCurrencyUseCase) {
        this.convertCurrencyUseCase = convertCurrencyUseCase;
        this.getAllConvertCurrencyUseCase = getAllConvertCurrencyUseCase;
    }

    @Operation(summary = "Convert currency", description = "Converts a specified amount from one currency to another using the current exchange rate.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Conversion successful",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CurrencyConversionDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid currency code provided"),
            @ApiResponse(responseCode = "404", description = "Currency not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public CurrencyConversionDTO convert(@RequestParam String from, @RequestParam String to) {
        log.info("Received currency conversion request from '{}' to '{}'", from, to);
        var result = convertCurrencyUseCase.execute(from, to);
        var dto = CurrencyConversionMapper.toDTO(result);
        log.info("Successfully converted currency from '{}' to '{}'. Conversion rate: {}", from, to, dto.getRate());
        return dto;
    }

    @Operation(summary = "Get all currency conversions", description = "Retrieves all stored currency conversions.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CurrencyConversionDTO.class)))
    })
    @GetMapping("/all")
    public List<CurrencyConversionDTO> getAllConversions() {
        log.info("Fetching all currency conversion records");
        return getAllConvertCurrencyUseCase.execute().stream()
                .map(CurrencyConversionMapper::toDTO)
                .collect(Collectors.toList());
    }

}
