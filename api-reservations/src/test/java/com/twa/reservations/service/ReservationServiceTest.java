package com.twa.reservations.service;

import com.twa.reservations.connector.CatalogConnector;
import com.twa.reservations.connector.response.CityDTO;
import com.twa.reservations.dto.ReservationDTO;
import com.twa.reservations.model.Reservation;
import com.twa.reservations.repository.ReservationRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;

import java.util.Optional;

import static com.twa.reservations.util.ReservationUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Check the functionality of the service")
class ReservationServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceTest.class);

    @Mock
    ReservationRepository repository;

    @Mock
    ConversionService conversionService;

    @Mock
    CatalogConnector catalogConnector;

    @InjectMocks
    private ReservationService service;

    @BeforeEach
    void initialize_each_test() {
        LOGGER.info("Initialize the context on each test");
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("should return the information of the reservation")
    @Test
    void getReservation_should_return_the_information() {

        // Given
        Reservation reservationModel = getReservation(1L, "BUE", "MAD");
        when(repository.getReservationById(1L)).thenReturn(Optional.of(reservationModel));

        ReservationDTO reservationDTO = getReservationDTO(1L, "BUE", "MAD");
        when(conversionService.convert(reservationModel, ReservationDTO.class)).thenReturn(reservationDTO);

        // When
        ReservationDTO result = service.getReservationById(1L);

        // Then
        verify(repository, Mockito.atMostOnce()).getReservationById(1L);
        verify(conversionService, Mockito.atMostOnce()).convert(reservationModel, ReservationDTO.class);
        verify(catalogConnector, Mockito.never()).getCity(any());

        assertAll(() -> assertNotNull(result), () -> assertEquals(reservationDTO, result));
    }

    @DisplayName("should return remove a reservation")
    @Test
    void delete_should_remove_a_reservation() {

        // Given
        Reservation reservationModel = getReservation(1L, "BUE", "MAD");
        when(repository.getReservationById(1L)).thenReturn(Optional.of(reservationModel));

        ReservationDTO reservationDTO = getReservationDTO(1L, "BUE", "MAD");
        when(conversionService.convert(reservationModel, ReservationDTO.class)).thenReturn(reservationDTO);

        doNothing().when(repository).delete(1L);

        // When
        service.delete(1L);

        // Then
        verify(repository, Mockito.atMostOnce()).delete(1L);

        verify(repository, Mockito.atMostOnce()).getReservationById(1L);
        verify(conversionService, Mockito.atMostOnce()).convert(reservationModel, ReservationDTO.class);
        verify(catalogConnector, Mockito.never()).getCity(any());
    }

    @DisplayName("should return save a reservation")
    @Test
    void save_should_return_the_information() {

        // Given
        Reservation reservationModel = getReservation(1L, "BUE", "MAD");
        when(repository.save(reservationModel)).thenReturn(reservationModel);

        ReservationDTO reservationDTO = getReservationDTO(null, "BUE", "MAD");
        when(conversionService.convert(reservationModel, ReservationDTO.class)).thenReturn(reservationDTO);
        when(conversionService.convert(reservationDTO, Reservation.class)).thenReturn(reservationModel);

        when(catalogConnector.getCity(any())).thenReturn(new CityDTO());

        // When
        ReservationDTO reservation = service.save(reservationDTO);

        // Then
        assertAll(() -> assertNotNull(reservation),
                () -> assertEquals("BUE", reservation.getItinerary().getSegment().get(0).getOrigin()),
                () -> assertEquals("MAD", reservation.getItinerary().getSegment().get(0).getDestination()));
    }

    @DisplayName("should return update a reservation")
    @Test
    void update_should_return_the_information() {

        // Given
        Reservation reservationModel = getReservation(1L, "BUE", "MIA");

        when(repository.getReservationById(1L)).thenReturn(Optional.of(reservationModel));
        when(repository.update(1L, reservationModel)).thenReturn(reservationModel);
        when(catalogConnector.getCity(any())).thenReturn(new CityDTO());

        ReservationDTO reservationDTO = getReservationDTO(1L, "BUE", "MIA");
        when(conversionService.convert(reservationModel, ReservationDTO.class)).thenReturn(reservationDTO);
        when(conversionService.convert(reservationDTO, Reservation.class)).thenReturn(reservationModel);

        // When
        ReservationDTO reservation = service.update(1L, reservationDTO);

        // Then
        assertAll(() -> assertNotNull(reservation),
                () -> assertEquals("BUE", reservation.getItinerary().getSegment().get(0).getOrigin()),
                () -> assertEquals("MIA", reservation.getItinerary().getSegment().get(0).getDestination()));
    }
}
