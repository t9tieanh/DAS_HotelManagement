package com.dashotel.hotelmanagement.mapper;

import com.dashotel.hotelmanagement.dto.common.AddressDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class) // Hoặc dùng @ExtendWith(SpringExtension.class) nếu dùng JUnit 5
@SpringBootTest
public class AddressMapperTest {

    @Autowired
    AddressMapper mapper;

    @Test
    public void testFromRawAddress() {
        AddressDTO dto = mapper.fromRawAddress("Quận 1, Hồ Chí Minh");
        System.out.println(dto.toString());
        assertNotNull(dto);
        assertEquals("Quận 1", dto.getDistrict());
        assertEquals("Hồ Chí Minh", dto.getCity());
    }
}
