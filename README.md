# 🌍 GeoLog Spring — Plataforma de Rastreamento de Frotas e Telemetria

> Sistema desenvolvido com arquitetura de **persistência poliglota** combinando bases relacionais e NoSQL para gestão de frotas, motoristas, veículos e telemetria geoespacial em tempo real.

---

## 🚀 Sobre o Projeto

O **GeoLog** é uma solução de back-end escalável projetada para monitorizar frotas de transporte. O projeto implementa o conceito de persistência poliglota, utilizando o banco de dados ideal para cada tipo de domínio de negócio:
* **H2 (Relacional / JPA):** Gestão estruturada e transacional de Motoristas e Veículos.
* **MongoDB (NoSQL / Geoespacial):** Armazenamento de alta performance de dados de Telemetria, aproveitando índices geoespaciais (`2dsphere`) para cálculos avançados de proximidade.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot** (Web, Data JPA, Data MongoDB)
* **H2 Database** (Consola em memória)
* **MongoDB** (Consultas geoespaciais com `GeoJsonPoint`)
* **Maven** (Gestão de dependências)
* **Postman** (Testes de API)

---

## ⚙️ Arquitetura e Estrutura de Pastas

O projeto segue uma arquitetura em camadas bem definida:
```text
com.logitech.geolog
│
├── controller     # Endpoints REST (API Layer)
├── dto            # Objetos de Transferência de Dados (Visão Unificada)
├── model          # Entidades (JPA para Relacional e Document para MongoDB)
├── repository     # Interfaces de Acesso a Dados (Spring Data)
├── service        # Regras de Negócio e Lógica de Integração
└── DataSeeder     # Inicializador de dados de teste e índices 2dsphere
