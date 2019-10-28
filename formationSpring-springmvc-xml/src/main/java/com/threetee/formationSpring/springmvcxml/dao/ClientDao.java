package com.threetee.formationSpring.springmvcxml.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.threetee.formationSpring.springmvcxml.model.Client;
@Repository
public interface ClientDao extends JpaRepository<Client, Long>{

}
