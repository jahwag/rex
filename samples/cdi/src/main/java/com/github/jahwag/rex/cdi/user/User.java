package com.github.jahwag.rex.cdi.user;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = User.FIND_BY_NAME, query = "SELECT u FROM User u WHERE u.name = :name")
})
@XmlRootElement
@Data
public class User {

    static final String FIND_BY_NAME = "User.findByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(length = 10)
    private String name;

    @Column(length = 10)
    private String password;

    @Column(length = 10)
    private String role;

}
