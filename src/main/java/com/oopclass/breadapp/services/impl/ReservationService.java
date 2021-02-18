package com.oopclass.breadapp.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oopclass.breadapp.models.Reservation;
import com.oopclass.breadapp.repository.ReservationRepository;
import com.oopclass.breadapp.services.IReservationService;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Service
public class ReservationService implements IReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public Reservation save(Reservation entity) {
		return reservationRepository.save(entity);
	}

	@Override
	public Reservation update(Reservation entity) {
		return reservationRepository.save(entity);
	}

	@Override
	public void delete(Reservation entity) {
		reservationRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		reservationRepository.deleteById(id);
	}

	@Override
	public Reservation find(Long id) {
		return reservationRepository.findById(id).orElse(null);
	}

	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Override
	public void deleteInBatch(List<Reservation> reservations) {
		reservationRepository.deleteInBatch(reservations);
	}
	
}
