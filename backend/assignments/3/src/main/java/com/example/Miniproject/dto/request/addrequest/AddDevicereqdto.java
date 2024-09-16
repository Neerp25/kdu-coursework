package com.example.Miniproject.dto.request.addrequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDevicereqdto {
    String houseId;
    String roomId;
    String kickstonId;
}
