package online.cybeer.simplecalendarapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.stream.Stream;
import online.cybeer.simplecalendarapi.dto.ErrorResponse;
import online.cybeer.simplecalendarapi.dto.event.EventResponse;
import online.cybeer.simplecalendarapi.service.EventService;
import online.cybeer.simplecalendarapi.utils.ResourceLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EventService eventService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        when(eventService.createEvent(any(), any())).thenReturn(new EventResponse("", "", "", null, null, ""));
    }

    private static Stream<Arguments> validationTestCases() {
        return Stream.of(
                arguments(
                        "event_request_missing_title.json",
                        "title",
                        "must not be blank"
                ),
                arguments(
                        "event_request_missing_timestamps.json",
                        "startTimestamp",
                        "must not be null"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("validationTestCases")
    void createEvent_ShouldReturnValidationErrors(
            String requestFile, String expectedField, String expectedMessage
    ) throws Exception {
        String jsonRequest = ResourceLoader.loadResourceAsString("prepared_data/events/" + requestFile);

        mockMvc.perform(post("/events")
                        .header("Idempotency-Key", "test-key")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"))
                .andExpect(jsonPath("$.errors[?(@.field == '%s')].message", expectedField)
                        .value(expectedMessage));
    }

    @ParameterizedTest
    @MethodSource("errorScenarios")
    void handleExceptions_ShouldReturnProperErrorResponses(
            String url,
            Class<? extends Exception> exceptionClass,
            String expectedCode,
            HttpStatus expectedStatus
    ) throws Exception {
        when(eventService.getEventById(any()))
                .thenThrow(exceptionClass);

        MvcResult result = mockMvc.perform(get(url))
                .andExpect(status().is(expectedStatus.value()))
                .andReturn();

        if (expectedStatus != HttpStatus.INTERNAL_SERVER_ERROR) {
            ErrorResponse response = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    ErrorResponse.class
            );
            assertEquals(expectedCode, response.code());
        }
    }

    private static Stream<Arguments> errorScenarios() {
        return Stream.of(
                arguments(
                        "/events/invalid-uuid",
                        MethodArgumentTypeMismatchException.class,
                        "INVALID_ARGUMENT_TYPE",
                        HttpStatus.BAD_REQUEST
                )
        );
    }
}
