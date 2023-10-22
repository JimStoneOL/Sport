package com.marat.controlworkbymarat.repository;

import com.marat.controlworkbymarat.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageModel,String> {
}
