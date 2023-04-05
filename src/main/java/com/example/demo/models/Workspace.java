package com.example.demo.models;

import com.example.demo.enums.Visibility;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO refactor code using lombok

@Entity
@Table(
        name = "workspaces"
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Workspace {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    //TODO Setup ForeignKey with OneToMany Relationship:
    //-Do Research beforehand
    @Column(
            name = "user_id"
    )
    private Long userId;

    @Column(
            name = "visibility"
    )
    private Visibility visibility;


    //constructor
//    public Workspace() {
//    }
//
//    public Workspace(Long userId) {
//        visibility = Visibility.PRIVATE;
//        this.userId = userId;
//    }
//
//    public Workspace(Long userId, Visibility visibility) {
//        this.userId = userId;
//        this.visibility = visibility;
//    }


    //getter-setter

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public Visibility getVisibility() {
//        return visibility;
//    }
//
//    public void setVisibility(Visibility visibility) {
//        this.visibility = visibility;
//    }
}
