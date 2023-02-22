package me.matvienkoeg.coursework3.services;

import me.matvienkoeg.coursework3.model.Color;
import me.matvienkoeg.coursework3.model.Size;
import me.matvienkoeg.coursework3.model.SocksBatch;

public interface SocksOnlineStoreService {
    void entrance (SocksBatch socksBatch);
    int  extradite (SocksBatch socksBatch);
    int writeOff (SocksBatch socksBatch);
    int getCount (Color color, Size size, int cottonMin, int cottonMax);
}
