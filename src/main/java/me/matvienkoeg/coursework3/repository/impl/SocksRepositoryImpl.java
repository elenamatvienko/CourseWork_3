package me.matvienkoeg.coursework3.repository.impl;

import me.matvienkoeg.coursework3.model.Socks;
import me.matvienkoeg.coursework3.model.SocksBatch;
import me.matvienkoeg.coursework3.repository.SocksRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SocksRepositoryImpl implements SocksRepository {

    public HashMap<Socks, Integer> socksMap = new HashMap<>();


    @Override
    public void addSocksBatch(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();

        if (socksMap.containsKey(socks)) {
            socksMap.replace(socks, socksMap.get(socks) + socksBatch.getQuantity());
        } else {
            socksMap.put(socks, socksBatch.getQuantity());
        }
    }

    @Override
    public int delete(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();
        int quantity = socksMap.get(socks);

        if (socksMap.containsKey(socks)) {

            if (quantity > socksBatch.getQuantity()) {
                socksMap.replace(socks, socksMap.get(socks) - socksBatch.getQuantity());
                return socksBatch.getQuantity();
            } else {
                socksMap.remove(socks);
                return quantity;
            }
        }
        return 0;
    }

    @Override
    public Map<Socks, Integer> getAll() {
        return socksMap;
    }
}
