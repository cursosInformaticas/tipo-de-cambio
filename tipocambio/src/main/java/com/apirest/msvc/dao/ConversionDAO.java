package com.apirest.msvc.dao;

import com.apirest.msvc.model.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionDAO extends JpaRepository<Conversion, Long> {
}
