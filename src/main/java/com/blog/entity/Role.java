package com.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long id;
    private String name;
}
