package com.marat.controlworkbymarat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
@NoArgsConstructor
@Entity
public class ImageModel {
    @Id
    private String name;
    @Lob
    @Column(columnDefinition = "bigint")
    private byte[] imageBytes;
}
