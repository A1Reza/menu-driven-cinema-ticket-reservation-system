# 🎬 Cinema Ticket Reservation System

> A **personal learning project** built to practice Java OOP, interface-driven
> design, in-memory data management, and PostgreSQL query writing.
> This is a study exercise — not production-ready software.

A menu-driven Java console application for managing cinema ticket reservations.
It models customers, movies, cinema halls, screenings, and tickets, and includes
a fixed-size waiting queue that automatically reassigns freed seats when a
reservation is cancelled. The project also ships 10 PostgreSQL query exercises
tested against the standard *sample employees* database provided in
**`employees.rar`**.

---

## 📑 Table of Contents

- [Overview](#-overview)
- [Learning Objectives](#-learning-objectives)
- [Features](#-features)
- [Architecture](#-architecture)
- [Class Model](#-class-model)
- [Project Structure](#-project-structure)
- [Main Menu](#-main-menu)
- [Validation Rules](#-validation-rules)
- [How to Run](#-how-to-run)
- [PostgreSQL Queries](#-postgresql-queries)
- [Sample Database (`employees.rar`)](#-sample-database-employeesrar)
- [Tech Stack](#-tech-stack)
- [Author](#-author)

---

## 📖 Overview

The system simulates a small cinema reservation workflow:

- A **Customer** registers with a unique username and a password.
- A **Movie** has a title, genre, duration, and IMDb rating.
- A **CinemaHall** has a unique name and a fixed number of seats.
- A **Screening** links a *Movie* to a *CinemaHall* and tracks how many seats
  are already reserved.
- A **Ticket** records a reservation for a *Customer* at a *Screening*, together
  with the moment it was created.
- When a screening is full, extra customers are placed into a
  **WaitingQueue** (fixed capacity of 20).
- When a reservation is cancelled, the freed seat is automatically assigned to
  the first waiting customer — if the queue is not empty.

All persistence is **in-memory** via `InMemoryDatabase` (`ArrayList`-backed),
and every entity has its own CRUD operation class implementing a common
`DatabaseOperation` interface.

The PostgreSQL part of the project is provided as `queries.sql` and uses the
standard **`employees.rar`** sample database included in the project root.

---

## 🎯 Learning Objectives

This exercise was written to practice and demonstrate:

| # | Topic |
|---|-------|
| 1 | Abstract classes and inheritance (`BaseModel` → `Customer`, `Movie`, ...) |
| 2 | Interface-driven design (`DatabaseOperation`) with generic `Object` casting |
| 3 | Separation between **model**, **repository**, and **service** layers |
| 4 | Using `ArrayList` only where allowed (`InMemoryDatabase`) and plain arrays elsewhere |
| 5 | Fixed-capacity queue logic (max 20 customers, shift-on-poll) |
| 6 | Controller composition — service instantiates operation classes manually |
| 7 | Menu-driven console I/O with `Scanner` |
| 8 | Input validation (length constraints, uniqueness, numeric ranges) |
| 9 | Time handling with `LocalDateTime` |
| 10 | PostgreSQL: `SUBSTRING`, `EXTRACT`, `TO_CHAR`, `AT TIME ZONE`, `COUNT(DISTINCT ...)`, `MIN`/`MAX`, `CONCAT`, `LIKE`, date arithmetic |

---

## ✨ Features

- **Full CRUD** for customers, movies, cinema halls, and screenings
- **Reservation lifecycle** — reserve and cancel tickets
- **Waiting queue management**
  - Customers land in the queue when a screening is full
  - Queue is a fixed-size `Customer[20]` array
  - Duplicate customers are rejected
  - Queue overflow produces a clear error message
- **Automatic seat reassignment**
  - On cancellation, the first waiting customer is automatically given the seat
- **Displays**
  - All movies
  - All screenings
  - Waiting queue for a specific screening ID (with indices)
- **Rich validation**
  - Unique usernames, unique movie titles, unique hall names
  - Username ≥ 6 chars, password ≥ 8 chars
  - IMDb rating in `[0.0, 10.0]`
  - Hall seat count > 0
- **10 PostgreSQL query exercises** (see below)

---

## 🏗 Architecture

Three clean layers, wired together by the controller:

```text
┌─────────────────────────────┐
│           Main              │   ← Console UI (menu-driven)
└──────────────┬──────────────┘
               │
┌──────────────▼──────────────┐
│ CinemaOperationManagement   │   ← Controller / Service
│  (instantiates ops manually)│
└──────────────┬──────────────┘
               │
┌──────────────▼──────────────┐
│    *DatabaseOperation       │   ← Repository layer (one per entity)
│   implements                │
│   DatabaseOperation         │
└──────────────┬──────────────┘
               │
┌──────────────▼──────────────┐
│      InMemoryDatabase       │   ← ArrayList storage (only ArrayList here)
└─────────────────────────────┘
```

**Design principles applied:**

- **Open/Closed** — new entities only need a new model + new operation class
- **Single Responsibility** — each operation class handles one entity only
- **Dependency composition** — `CinemaOperationManagement` creates its
  dependencies explicitly (no framework, no DI container)
- **Interface segregation** — `DatabaseOperation` exposes only the four CRUD
  methods

---

## 🧩 Class Model

### `BaseModel` (abstract)

```java
public abstract class BaseModel {
    private long id;
    public BaseModel(long id) { this.id = id; }
    public long getId() { return id; }
}
```

### Entity classes

| Class | Extends | Key Fields |
|-------|---------|------------|
| `Customer` | `BaseModel` | `firstName`, `lastName`, `phoneNumber`, `username`, `password` |
| `Movie` | `BaseModel` | `title`, `genre`, `durationInMinutes`, `imdbRate` |
| `CinemaHall` | `BaseModel` | `hallName`, `totalSeats` |
| `Screening` | `BaseModel` | `movie`, `hall`, `waitingQueue`, `reservedSeats` |
| `Ticket` | `BaseModel` | `customer`, `screening`, `reservationTime` |

### `WaitingQueue` (plain class)

Holds a `Customer[] queue` of length 20 with `add`, `poll`, `isFull`,
`isEmpty`, `size`, and `display`.

### `DatabaseOperation` (interface)

```java
public interface DatabaseOperation {
    void save(Object object);
    void delete(long id);
    void update(Object object);
    Object find(long id);
}
```

Implemented by `CustomerDatabaseOperation`, `MovieDatabaseOperation`,
`CinemaHallDatabaseOperation`, `ScreeningDatabaseOperation`, and
`TicketDatabaseOperation`. Each implementation casts `Object` to the concrete
type and operates on the corresponding `InMemoryDatabase` list.

---

## 📁 Project Structure

```text
project-root/
├── src/
│   └── com/cinema/
│       ├── Main.java
│       ├── model/
│       │   ├── BaseModel.java
│       │   ├── Customer.java
│       │   ├── Movie.java
│       │   ├── CinemaHall.java
│       │   ├── Screening.java
│       │   ├── Ticket.java
│       │   └── WaitingQueue.java
│       ├── repository/
│       │   ├── InMemoryDatabase.java
│       │   ├── DatabaseOperation.java
│       │   ├── CustomerDatabaseOperation.java
│       │   ├── MovieDatabaseOperation.java
│       │   ├── CinemaHallDatabaseOperation.java
│       │   ├── ScreeningDatabaseOperation.java
│       │   └── TicketDatabaseOperation.java
│       └── service/
│           └── CinemaOperationManagement.java
├── queries.sql         ← 10 PostgreSQL queries
├── employees.rar       ← sample employees database (schema + data)
└── README.md
```

---

## 🧭 Main Menu

```text
         ------------ Cinema Ticket Reservation System menu ------------
1.  Add Customer        10. Add Screening
2.  Update Customer     11. Update Screening
3.  Delete Customer     12. Delete Screening
4.  Add Movie           13. Reserve Ticket
5.  Update Movie        14. Cancel Reservation
6.  Delete Movie        15. Display Movies
7.  Add Cinema Hall     16. Display Screenings
8.  Update Cinema Hall  17. Display Waiting Queue by Screening ID
9.  Delete Cinema Hall  18. Exit
```

**Reservation workflow:**

1. Pick option **13**, enter a Customer ID and a Screening ID.
2. If seats remain → a `Ticket` is created and saved; `reservedSeats++`.
3. If the screening is full → the customer is added to that screening's
   waiting queue (unless the queue is also full).
4. On cancellation (**option 14**) → ticket is deleted, `reservedSeats--`, and
   if the queue is non-empty the first waiting customer is automatically
   re-reserved into the freed seat.

---

## ✅ Validation Rules

| Rule | Where enforced |
|------|----------------|
| Username unique | `CinemaOperationManagement.addCustomer` / `updateCustomer` |
| Username ≥ 6 characters | `CinemaOperationManagement.addCustomer` / `updateCustomer` |
| Password ≥ 8 characters | `CinemaOperationManagement.addCustomer` / `updateCustomer` |
| Movie title unique | `CinemaOperationManagement.addMovie` / `updateMovie` |
| Cinema hall name unique | `CinemaOperationManagement.addHall` / `updateHall` |
| Hall `totalSeats` > 0 | `CinemaOperationManagement.addHall` / `updateHall` |
| IMDb rate in `[0.0, 10.0]` | `addMovie` / `updateMovie` |
| Waiting queue max 20 | `WaitingQueue.isFull` |
| No duplicate customers in queue | `WaitingQueue.add` |
| Screening ID unique | `CinemaOperationManagement.addScreening` |

---

## ▶ How to Run

### Requirements

- **Java 17+** (uses `switch` arrow syntax and `LocalDateTime`)
- **PostgreSQL 13+** (only for the SQL exercises — optional for the Java app)
- A tool to extract `.rar` files (`unrar`, `7z`, or WinRAR) for the sample DB

### Compile and run

From the project root (the folder containing `src`):

**Linux / macOS:**

```bash
javac -d out $(find src -name "*.java")
java -cp out com.cinema.Main
```

**Windows (Command Prompt):**

```bat
javac -d out src\com\cinema\*.java src\com\cinema\model\*.java src\com\cinema\repository\*.java src\com\cinema\service\*.java
java -cp out com.cinema.Main
```

**IntelliJ IDEA / Eclipse:** open the project folder, mark `src` as the source
root, and run `Main`.

---

## 🐘 PostgreSQL Queries

The file `queries.sql` contains 10 exercises. All queries were tested on the
**sample employees database** provided in `employees.rar`.

### Query overview

| # | Topic | Techniques |
|---|-------|-----------|
| 1 | First three chars of `first_name`, hire year | `SUBSTRING`, `EXTRACT`, `LIKE` |
| 2 | Count of distinct titles | `COUNT(DISTINCT ...)` |
| 3 | Department names in uppercase (excluding `d001`, `d002`) | `UPPER`, `NOT IN` |
| 4 | Full name + current time in UTC | `CONCAT`, `AT TIME ZONE` |
| 5 | `hire_date` formatted as `Day, DD Month YYYY` | `TO_CHAR` |
| 6 | `birth_date` converted to Asia/Tehran | `AT TIME ZONE` |
| 7 | Employees older than 45 + London time | `AGE`, `AT TIME ZONE` |
| 8 | `hire_date` as `HH24:MI DD/MM/YYYY` for `S*` first names | `TO_CHAR`, `LIKE` |
| 9 | Min / max / range salary + from_date in UTC | `MIN`, `MAX`, `AT TIME ZONE` |
| 10 | Titles + `[title] started from [from_date]` + Tehran time | `CONCAT`, `LIKE`, `AT TIME ZONE` |

---

## 🗄 Sample Database (`employees.rar`)

The **`employees.rar`** archive in the project root contains the **standard
PostgreSQL employee sample database** used to test `queries.sql`. Extract it
before running the queries.

### Contents of `employees.rar`

| File | Content |
|------|---------|
| `employees.sql` | Schema for `employees`, `departments`, `dept_manager`, `dept_emp`, `titles`, `salaries` |
| `load_departments.sql` | Data for `departments` |
| `load_employees.sql` | Data for `employees` |
| `load_dept_emp.sql` | Data for `dept_emp` |
| `load_dept_manager.sql` | Data for `dept_manager` |
| `load_titles.sql` | Data for `titles` |
| `load_salaries.sql` | Data for `salaries` |

### Loading the database

```bash
# 1. Create a database
createdb employees

# 2. Extract the archive (from the project root)
unrar x employees.rar      # or: 7z x employees.rar

# 3. Load schema + data (run psql from the extracted folder)
psql -d employees -f employees.sql

# 4. Run the queries
psql -d employees -f queries.sql
```

> **Note:** `employees.sql` uses `\i` directives to include the `load_*.sql`
> files, so **`psql` must be run from the same folder that contains them**
> (i.e. inside the extracted `employees` folder).

---

## 🛠 Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 17+ |
| Build | Plain `javac` (no Maven / Gradle) |
| Console I/O | `java.util.Scanner` |
| Time | `java.time.LocalDateTime` |
| Storage | `java.util.ArrayList` (inside `InMemoryDatabase` only) |
| Database (exercises) | PostgreSQL 13+ |
| SQL client | `psql` |
| Sample DB | `employees.rar` |

---

## 👤 Author

**Reza Asadipour**

---

> **Disclaimer:** This repository is a personal study exercise. It is designed
> to demonstrate core Java and SQL skills on a small, self-contained domain,
> and is **not** intended for real-world deployment.
