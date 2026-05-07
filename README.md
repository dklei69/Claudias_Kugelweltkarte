# 🌍 Claudias Kugelweltkarte

A dynamic, interactive web map application based on the fictional horror world from Claudia Roman's novel series. The world evolves with every phase transition, driven by Markov chains.

🔗 **Live Demo:** [claudias-kugelweltkarte.onrender.com](https://claudias-kugelweltkarte.onrender.com)

---

## About the Project

Claudias Kugelweltkarte is a Spring Boot web application that simulates a living, breathing world. A 50×50 grid of biome cells transitions between states (Water, Mountain, Plain) according to defined probability rules, implemented as a Markov chain.

The world exists in two phases: a **light phase** and a **dark phase**. With each phase transition, the biomes shift according to their transition probabilities. Certain cells can be **stabilized** to prevent them from changing.

---

## Features

- 🗺️ **Dynamic 50×50 grid** with three biome types: Water, Mountain, Plain
- 🔁 **Markov chain-driven phase transitions** with configurable probabilities
- 📌 **Fixed city point** (Kugelstadt / SPHERE) at the center of the map
- 🛡️ **Stabilizer mechanic** — click cells to protect them from transitions (max. 25)
- 🌗 **Light and dark phase** with distinct visual themes
- 🔍 **Phase history search** — look up and display any past phase
- 🕹️ **Historic mode** — view past phases with disabled controls
- 💾 **H2 in-memory database** for phase persistence during runtime
- 📖 **Biome legend** for quick reference
- 🌐 **Embedded in a Wix website** via iframe

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 21, Spring Boot |
| Template Engine | Thymeleaf (server-side HTML generation) |
| Frontend | CSS, JavaScript |
| Database | H2 (in-memory) |
| ORM | Spring Data JPA |
| Testing | JUnit 5, Mockito (TDD approach) |
| Build | Maven |
| Deployment | Docker, Render |
| Other | Lombok, Jackson |

---

## Architecture

The application follows the **MVC pattern**:

- **Model** - `BiomeType`, `Cell`, `Grid`, `Phase`, `StabilizerConfig`
- **Service** - `BiomeMarkovService` (implements `MarkovService`), `StabilizerService`
- **Controller** - `MapController`
- **Repository** - `PhaseRepository`, `StabilizerConfigRepository`

The Markov chain logic lives in `BiomeMarkovService`. For each non-fixed, non-stabilized cell, a random value is compared against the cumulative transition probabilities of the current biome, determining the next biome state.

---

## The Markov Chain

Each biome has defined transition probabilities to the other biome types:

| From \ To | Water | Mountain | Plain |
|---|---|---|---|
| Water | 0.4 | 0.1 | 0.5 |
| Mountain | 0.2 | 0.5 | 0.3 |
| Plain | 0.4 | 0.3 | 0.3 |

A random `double` value between 0.0 and 1.0 is generated per cell. It is compared against the cumulative probabilities to determine the new biome, ensuring statistically correct transitions over time.

---

## Testing

The project was developed using **Test-Driven Development (TDD)**. Key test highlights:

- Unit tests for `BiomeMarkovService` and `StabilizerService` using Mockito
- A **seed-based test** for the Markov transition logic. The outcomes are probabilistic, a fixed `Random` seed ensures reproducible, deterministic test results

---

## Getting Started

### Prerequisites

- Java 21
- Maven
- Docker (optional, for containerized deployment)

### Run locally

```bash
git clone https://github.com/dklei69/Claudias_Kugelweltkarte.git
cd Claudias_Kugelweltkarte
mvn spring-boot:run
```

Open your browser at `http://localhost:8080/map`

### Run with Docker

```bash
docker build -t kugelweltkarte .
docker run -p 8080:8080 kugelweltkarte
```

---

## Planned Features

- 👾 Monsters and electric fields in the dark phase
- 🌫️ Fog video overlay (atmospheric effect)
- 📱 Responsive design
- 🐘 Migration from H2 to PostgreSQL for persistent data

---

## About

Developed by **Daniela Kiel** as part of a professional retraining program (*Umschulung*) to become a *Fachinformatikerin für Anwendungsentwicklung* in Germany.

Inspired by the fictional world of **Claudia Roman**.
