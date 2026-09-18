package com.logitech.geolog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "motoristas")
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnh;
    private String status;

    public Motorista() {}

    public Motorista(String nome, String cnh, String status) {
        this.nome = nome;
        this.cnh = cnh;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCnh() { return cnh; }
    public void setCnh(String cnh) { this.cnh = cnh; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}