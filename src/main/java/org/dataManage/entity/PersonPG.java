package org.dataManage.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Person")
public class PersonPG {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uid;
    private String userId;
    private String name;
    private String email;
    private String password;


}
