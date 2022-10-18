package com.catalisa.redesolidaria.repository;

import com.catalisa.redesolidaria.model.TipoDaDeficienciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD
public interface TipoDaDeficienciaReposito {
=======
public interface TipoDaDeficienciaRepository extends JpaRepository<TipoDaDeficienciaModel, Long> {
>>>>>>> rhaniel
}
