package com.example.serviceImpl;

import com.example.dto.BookingDto;
import com.example.entity.Booking;
import com.example.entity.User;
import com.example.entity.Vehicle;
import com.example.enums.BookingStatus;
import com.example.enums.VehicleStatus;
import com.example.repository.BookingRepository;
import com.example.repository.UserRepository;
import com.example.repository.VehicleRepository;
import com.example.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;
    @Override
    public BookingDto createBooking(Long userId, Long vehicleId, BookingDto bookingDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User Not Found"));
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(()-> new RuntimeException("Vehicle Not Found"));
        Booking booking = new Booking();
        booking.setPickupLocation(bookingDto.getPickupLocation());
        booking.setDropLocation(bookingDto.getDropLocation());
        booking.setPickupDate(bookingDto.getPickupDate());
        booking.setReturnDate(bookingDto.getReturnDate());

        long days = ChronoUnit.DAYS.between(
                bookingDto.getPickupDate(),
                bookingDto.getReturnDate())+1;
        booking.setTotalDays((int) days);

        double amount = days*vehicle.getVariant().getPricePerDay();

        booking.setTotalAmount(amount);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setUser(user);
        booking.setVehicle(vehicle);
        vehicle.setStatus(VehicleStatus.BOOKED);
        bookingRepository.save(booking);
        vehicleRepository.save(vehicle);

        return modelMapper.map(booking , BookingDto.class);
    }

    @Override
    public BookingDto getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()->new RuntimeException("Booking Not Found"));
        return modelMapper.map(booking,BookingDto.class);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(booking ->
                        modelMapper.map(booking,BookingDto.class))
                .toList();
    }

    @Override
    public List<BookingDto> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(booking -> modelMapper
                        .map(booking ,BookingDto.class))
                .toList();
    }

    @Override
    public BookingDto cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking Not Found"));
        booking.setStatus(BookingStatus.CANCELLED);
        Vehicle vehicle = booking.getVehicle();
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        vehicleRepository.save(vehicle);
        Booking updated = bookingRepository.save(booking);

        return modelMapper.map(updated,BookingDto.class);
    }
}
