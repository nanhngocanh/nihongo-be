package com.hedspi.nihongobe.entity;

import com.hedspi.nihongobe.enums.Type;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "lesson_theory")
@EntityListeners(AuditingEntityListener.class)
public class LessonTheory {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @Column(name = "term")
    private String term;

    @Column(name = "definition")
    private String definition;

    @Column(name = "description")
    private String description;

    public LessonTheory(Integer id, Lesson lesson, Type type, String term, String definition, String description) {
        this.id = id;
        this.lesson = lesson;
        this.type = type;
        this.term = term;
        this.definition = definition;
        this.description = description;
    }

    public LessonTheory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
