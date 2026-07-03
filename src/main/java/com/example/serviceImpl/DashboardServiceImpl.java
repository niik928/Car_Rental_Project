package com.example.serviceImpl;

import com.example.entity.DashboardDto;
import com.example.repository.BookingRepository;
import com.example.repository.CarRepository;
import com.example.repository.PaymentRepository;
import com.example.repository.UserRepository;
import com.example.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public DashboardDto getDashboardData() {

        DashboardDto dto = new DashboardDto();

        dto.setTotalUsers(userRepository.count());
        dto.setTotalBookings(bookingRepository.count());
        dto.setTotalCars(carRepository.count());
        dto.setTotalRevenue(paymentRepository.getTotalRevenue());

        return dto;
    }
}