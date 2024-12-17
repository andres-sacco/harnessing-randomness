package com.twa.reservations.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Instancio.gen;
import static org.instancio.Select.field;

@ExtendWith(InstancioExtension.class)
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
        Model<SegmentDTO> segmentModel = Instancio.of(SegmentDTO.class).set(field(SegmentDTO::getOrigin), "NYC")
                .set(field(SegmentDTO::getDestination), "LAX")
                .set(field(SegmentDTO::getDeparture), "2024-07-01T10:00:00")
                .set(field(SegmentDTO::getArrival), "2024-07-01T14:00:00").set(field(SegmentDTO::getCarrier), "AA")
                .toModel();

        ItineraryDTO itinerary = Instancio.of(ItineraryDTO.class)
                .set(field(ItineraryDTO::getSegment), List.of(Instancio.create(segmentModel))).create();

        ReservationDTO reservation = Instancio.of(ReservationDTO.class)
                .set(field(ReservationDTO::getPassengers), Collections.emptyList()) // Set an empty list
                .set(field(ReservationDTO::getItinerary), itinerary).create();

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
        Model<SegmentDTO> segmentModel = Instancio.of(SegmentDTO.class).set(field(SegmentDTO::getOrigin), "NYC")
                .set(field(SegmentDTO::getDestination), "LAX")
                .set(field(SegmentDTO::getDeparture), "2024-07-01T10:00:00")
                .set(field(SegmentDTO::getArrival), "2024-07-01T14:00:00").set(field(SegmentDTO::getCarrier), "AA")
                .toModel();

        ItineraryDTO itinerary = Instancio.of(ItineraryDTO.class)
                .set(field(ItineraryDTO::getSegment), List.of(Instancio.create(segmentModel))).create();

        PassengerDTO passenger = Instancio.of(PassengerDTO.class).set(field(PassengerDTO::getFirstName), "John")
                .set(field(PassengerDTO::getLastName), "Doe").set(field(PassengerDTO::getDocumentNumber), "12345678")
                .set(field(PassengerDTO::getDocumentType), "Passport")
                .set(field(PassengerDTO::getBirthday), gen().temporal().localDate().past().get()).create();

        ReservationDTO reservation = Instancio.of(ReservationDTO.class)
                .set(field(ReservationDTO::getPassengers), List.of(passenger))
                .set(field(ReservationDTO::getItinerary), itinerary).create();
        // When
        Set<ConstraintViolation<ReservationDTO>> violations = validator.validate(reservation);

        // Then
        assertThat(violations).isEmpty();
    }

    @Test
    void should_fail_when_itinerary_is_null() {
        // Given
        ReservationDTO reservation = new ReservationDTO();

        PassengerDTO passenger = Instancio.of(PassengerDTO.class).set(field(PassengerDTO::getFirstName), "John")
                .set(field(PassengerDTO::getLastName), "Doe").set(field(PassengerDTO::getDocumentNumber), "12345678")
                .set(field(PassengerDTO::getDocumentType), "Passport")
                .set(field(PassengerDTO::getBirthday), gen().temporal().localDate().past().get()).create();

        reservation.setPassengers(List.of(passenger));
        reservation.setItinerary(null);

        // When
        Set<ConstraintViolation<ReservationDTO>> violations = validator.validate(reservation);

        // Then
        assertThat(violations).hasSize(1);
    }
}
