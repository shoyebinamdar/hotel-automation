package com.sahaj.hotelautomation.output;

import com.sahaj.hotelautomation.entities.Hotel;

public interface OutputListener {
    void write(Hotel hotel);
}
