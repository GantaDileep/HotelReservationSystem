package com.demo.hotelreservation.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.demo.hotelreservation.entity.Reservation;
import com.demo.hotelreservation.enums.ReservationStatus;
import com.demo.hotelreservation.repository.ReservationRepository;

@Component
public class ReservationScheduler {

    private static final Logger logger =
            LoggerFactory.getLogger(ReservationScheduler.class);

    private final ReservationRepository reservationRepository;

    public ReservationScheduler(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Scheduled(fixedRate = 30000)
    public void checkPendingReservations() {

        List<Reservation> pendingReservations =
                reservationRepository.findByReservationStatus(
                        ReservationStatus.PENDING);

        logger.info("Pending reservations count: {}",
                pendingReservations.size());
    }
}