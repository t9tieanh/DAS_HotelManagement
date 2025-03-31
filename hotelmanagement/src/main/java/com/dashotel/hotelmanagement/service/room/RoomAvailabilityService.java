package com.dashotel.hotelmanagement.service.room;

import com.dashotel.hotelmanagement.entity.room.RoomAvailabilityEntity;
import com.dashotel.hotelmanagement.repository.room.RoomAvailabilityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoomAvailabilityService {

}
