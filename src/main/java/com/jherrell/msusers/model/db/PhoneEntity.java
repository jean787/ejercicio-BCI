package com.jherrell.msusers.model.db;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "phones")
@Data
public class PhoneEntity implements Serializable {

    @Id
    @Column(name = "phone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "phone_number")
    private String number;
    @Column(name = "phone_city_code")
    private String cityCode;
    @Column(name = "phone_country_code")
    private String countryCode;
}
