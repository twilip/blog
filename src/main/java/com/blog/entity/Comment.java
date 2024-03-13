package com.blog.entity;

import com.blog.payload.PostDto;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String  body;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;



}
