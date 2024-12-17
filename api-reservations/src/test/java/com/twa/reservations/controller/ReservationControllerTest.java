package com.twa.reservations.controller;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.twa.reservations.dto.ReservationDTO;
import com.twa.reservations.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@DisplayName("Check the functionality of the controller")
class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("should return the information of all reservations")
    @Test
    void getReservations_should_return_the_information() {
        // Given
        List<ReservationDTO> reservations = Arrays.asList(new ReservationDTO(), new ReservationDTO());
        when(reservationService.getReservations()).thenReturn(reservations);

        // When
        ResponseEntity<List<ReservationDTO>> response = reservationController.getReservations();

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(reservations);
        verify(reservationService, times(1)).getReservations();
    }

    @DisplayName("should return the information of the reservation")
    @Test
    void getReservation_should_return_the_information() {
        // Given
        Long id = 1L;
        ReservationDTO reservationDTO = new ReservationDTO();
        when(reservationService.getReservationById(id)).thenReturn(reservationDTO);

        // When
        ResponseEntity<ReservationDTO> response = reservationController.getReservationById(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(reservationDTO);
        verify(reservationService, times(1)).getReservationById(id);
    }

    @DisplayName("should return save a reservation")
    @Test
    void save_should_return_the_information() {
        // Given
        ReservationDTO inputReservation = new ReservationDTO();
        ReservationDTO savedReservation = new ReservationDTO();
        when(reservationService.save(inputReservation)).thenReturn(savedReservation);

        // When
        ResponseEntity<ReservationDTO> response = reservationController.save(inputReservation);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(savedReservation);
        verify(reservationService, times(1)).save(inputReservation);
    }

    @DisplayName("should return update a reservation")
    @Test
    void update_should_return_the_information() {
        // Given
        Long id = 1L;
        ReservationDTO inputReservation = new ReservationDTO();
        ReservationDTO updatedReservation = new ReservationDTO();
        when(reservationService.update(id, inputReservation)).thenReturn(updatedReservation);

        // When
        ResponseEntity<ReservationDTO> response = reservationController.update(id, inputReservation);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(updatedReservation);
        verify(reservationService, times(1)).update(id, inputReservation);
    }

    @DisplayName("should return remove a reservation")
    @Test
    void delete_should_remove_a_reservation() {
        // Given
        Long id = 1L;
        doNothing().when(reservationService).delete(id);

        // When
        ResponseEntity<Void> response = reservationController.delete(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(reservationService, times(1)).delete(id);
    }

}
