package com.br.desafio.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    /**
     * before persist is called for a new entity – @PrePersist
     * after persist is called for a new entity – @PostPersist
     * before an entity is removed – @PreRemove
     * after an entity has been deleted – @PostRemove
     * before the update operation – @PreUpdate
     * after an entity is updated – @PostUpdate
     * after an entity has been loaded – @PostLoad
     */

    @PrePersist
    public void onCreate() {
        if (Objects.isNull(getUuid())) {
            setUuid(UUID.randomUUID());
        }
    }

}
