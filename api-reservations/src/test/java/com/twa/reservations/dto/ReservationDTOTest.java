package com.twa.reservations.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ReservationDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void should_fail_when_passengers_list_is_empty() {
        // Given
        ReservationDTO reservation = new ReservationDTO();
        reservation.setPassengers(Collections.emptyList());
        reservation.setItinerary(new ItineraryDTO());

        // When
        Set<ConstraintViolation<ReservationDTO>> violations = validator.validate(reservation);

        // Then
        assertThat(violations).hasSize(1);

        ConstraintViolation<ReservationDTO> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("You need at least one passenger");
    }

    @Test
    void should_pass_when_passengers_are_provided() {
        // Given
        ReservationDTO reservation = new ReservationDTO();
        PassengerDTO passenger = new PassengerDTO();
        passenger.setFirstName("aasa");
        passenger.setLastName("23");
        passenger.setDocumentNumber("3434");
        passenger.setDocumentType("223423");
        reservation.setPassengers(List.of(passenger));
        reservation.setItinerary(new ItineraryDTO());

        // When
        Set<ConstraintViolation<ReservationDTO>> violations = validator.validate(reservation);

        // Then
        assertThat(violations).isEmpty();
    }

    @Test
    void should_fail_when_itinerary_is_null() {
        // Given
        ReservationDTO reservation = new ReservationDTO();
        PassengerDTO passenger = new PassengerDTO();
        reservation.setPassengers(List.of(passenger));
        reservation.setItinerary(null);

        // When
        Set<ConstraintViolation<ReservationDTO>> violations = validator.validate(reservation);

        // Then
        assertThat(violations).hasSize(4);
    }
}
