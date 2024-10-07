package com.weather.weatherCB.repository;


import com.weather.weatherCB.entity.Consultas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consultas,Integer> {
}
