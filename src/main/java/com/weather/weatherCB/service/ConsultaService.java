package com.weather.weatherCB.service;

import com.weather.weatherCB.entity.Consultas;
import com.weather.weatherCB.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;

    public void save(Consultas consultas){
        consultaRepository.save(consultas);
    }


}
