package me.matvienkoeg.coursework3.services.impl;

import com.sun.jdi.connect.VMStartException;
import lombok.AllArgsConstructor;
import me.matvienkoeg.coursework3.exception.ValidationException;
import me.matvienkoeg.coursework3.model.Color;
import me.matvienkoeg.coursework3.model.Size;
import me.matvienkoeg.coursework3.model.Socks;
import me.matvienkoeg.coursework3.model.SocksBatch;
import me.matvienkoeg.coursework3.repository.SocksRepository;
import me.matvienkoeg.coursework3.services.SocksOnlineStoreService;
import me.matvienkoeg.coursework3.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class SocksOnlineStoreServiceImpl implements SocksOnlineStoreService {
    private final SocksRepository socksRepository;
    private final ValidationService validationService;


    @Override
    public void entrance(SocksBatch socksBatch) {
        if (!validationService.validate(socksBatch)) {
            throw new ValidationException();
        }
        socksRepository.addSocksBatch(socksBatch);
    }

    @Override
    public int extradite(SocksBatch socksBatch) {
        if (!validationService.validate(socksBatch)) {
            throw new ValidationException();
        }
        return socksRepository.delete(socksBatch);
    }

    @Override
    public int writeOff(SocksBatch socksBatch) {
        if (!validationService.validate(socksBatch)) {
            throw new ValidationException();
        }
        return socksRepository.delete(socksBatch);
    }

    @Override
    public int getCount(Color color, Size size, int cottonMin, int cottonMax) {
   
        if (!validationService.validate(color, size, cottonMin, cottonMax)) {
            throw new ValidationException();
        }
        Map<Socks, Integer> socksMap = socksRepository.getAll();
        int count = 0;
        for (Map.Entry<Socks, Integer> socksType : socksMap.entrySet()) {
            Socks socks = socksType.getKey();
            if (socks.getColor().equals(color)
                    && socks.getSize().equals(size)
                    && socks.getCottonPart() >= cottonMin
                    && socks.getCottonPart() <= cottonMax) {
                 count += socksType.getValue();
            }
        }

        return 0;
    }

}
