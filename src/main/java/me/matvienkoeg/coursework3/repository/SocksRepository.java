package me.matvienkoeg.coursework3.repository;

import me.matvienkoeg.coursework3.model.Socks;
import me.matvienkoeg.coursework3.model.SocksBatch;

import java.util.Map;

public interface SocksRepository {
    void addSocksBatch (SocksBatch socksBatch);
    int delete (SocksBatch socksBatch);
    Map <Socks, Integer> getAll();
}