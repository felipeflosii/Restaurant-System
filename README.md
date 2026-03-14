# RestaurantSystem

REST API for restaurant order management, built with Java and Spring Boot. The project simulates a real backend used in delivery or internal management systems, applying clean architecture, relational data modeling, and layered organization.

---

## Tech Stack

| Technology | Details |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.0.3 |
| Persistence | Spring Data JPA + Hibernate |
| Database | PostgreSQL 16 |
| Migrations | Flyway (planned — Level 3) |
| Validation | Jakarta Validation (Bean Validation) |
| Security | Spring Security + JWT (planned — Level 3) |
| Boilerplate | Lombok |
| Build Tool | Maven 3.9.12 |
| Containerization | Docker + Docker Compose |

---

## Project Structure

```
src/main/java/br/com/flosi/restaurant/
├── controllers/        REST controllers (HTTP layer)
├── services/           Business logic layer
├── repositories/       Spring Data JPA interfaces
├── models/             JPA entities
│   └── enums/          Domain enums (DishCategory, OrderStatus, RestaurantSpecialty)
├── dtos/               Data Transfer Objects (request/response)
├── exceptions/         Global exception handling
└── security/           Spring Security + JWT (planned)
```

---

## Domain Model

### Entities

| Entity | Description |
|---|---|
| Restaurant | Represents a restaurant with name, address, and specialty |
| Dish | A dish offered by a restaurant with name, description, category, and price |
| Order | A customer order linked to dishes, with an auto-calculated total and lifecycle status |

### Relationships

| Relationship | Type |
|---|---|
| Restaurant → Dish | One-to-Many |
| Order → Dish | Many-to-Many (via `order_items` table) |

### Enums

| Enum | Values |
|---|---|
| DishCategory | `APPETIZER`, `SOUP`, `SALAD`, `MAIN_COURSE`, `PIZZA`, `PASTA`, `SEAFOOD`, `GRILL`, `VEGETARIAN`, `VEGAN`, `DESSERT`, `BEVERAGE`, `COMBO`, `SPECIAL`, and more |
| OrderStatus | `CREATED` → `CONFIRMED` → `PREPARING` → `READY` → `OUT_FOR_DELIVERY` → `DELIVERED` / `CANCELLED` / `PAID` |
| RestaurantSpecialty | `ITALIAN`, `JAPANESE`, `BRAZILIAN`, `MEXICAN`, `CHINESE`, `AMERICAN`, `FRENCH`, `MEDITERRANEAN`, `SEAFOOD`, `VEGETARIAN`, `VEGAN`, `FAST_FOOD`, `PIZZA`, `STEAKHOUSE`, `BAKERY`, `CAFE`, `BUFFET`, `FUSION` |

---

## API Endpoints

### Restaurants — `/restaurants`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/restaurants` | List all restaurants |
| GET | `/restaurants/{id}` | Get a restaurant by ID |
| POST | `/restaurants` | Create a new restaurant |
| PUT | `/restaurants/{id}` | Update a restaurant |
| DELETE | `/restaurants/{id}` | Delete a restaurant |
| GET | `/restaurants/{id}/dishes` | List all dishes of a restaurant |
| POST | `/restaurants/{id}/dishes` | Create a dish linked to a restaurant |

### Dishes — `/dishes`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/dishes` | List all dishes |
| GET | `/dishes/{id}` | Get a dish by ID |
| POST | `/dishes` | Create a new dish |
| PUT | `/dishes/{id}` | Update a dish |
| DELETE | `/dishes/{id}` | Delete a dish |

### Orders — `/orders`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/orders` | List all orders |
| GET | `/orders/{id}` | Get an order by ID |
| POST | `/orders` | Create a new order (total auto-calculated) |
| DELETE | `/orders/{id}` | Delete an order |

---

## Getting Started

### Prerequisites

- Docker Desktop installed and running

### Run with Docker

```bash
git clone https://github.com/felipeflosii/RestaurantSystem.git
cd RestaurantSystem
docker compose up --build
```

The API starts on `http://localhost:8080`. The PostgreSQL database is automatically provisioned by Docker Compose — no external database setup required.

### Run Locally (without Docker)

```bash
# Requires Java 21 and a running PostgreSQL instance
git clone https://github.com/felipeflosii/RestaurantSystem.git
cd RestaurantSystem
./mvnw spring-boot:run
```

---

## Example Requests

**Create a restaurant:**
```json
POST /restaurants

{
  "name": "Restaurante Lar Nobres",
  "address": "Rua Conceição, 292",
  "specialty": "STEAKHOUSE"
}
```

**Create a dish linked to a restaurant:**
```json
POST /restaurants/1/dishes

{
  "name": "Picanha na Brasa",
  "description": "Picanha grelhada na brasa com acompanhamento de farofa e vinagrete.",
  "category": "GRILL",
  "price": 89.90
}
```

**Create an order:**
```json
POST /orders

{
  "name": "Carlos Silva",
  "dishIds": [1, 2]
}
```

---

## API in Action

**POST /restaurants — Create a restaurant**
![POST Restaurant](docs/screenshots/post-restaurant.png)

**POST /restaurants/{id}/dishes — Create a dish linked to a restaurant**
![POST Restaurant Dishes](docs/screenshots/post-restaurant-dishes.png)

**GET /restaurants/{id}/dishes — List all dishes of a restaurant**
![GET Restaurant Dishes](docs/screenshots/get-restaurant-dishes.png)

**POST /orders — Create an order with auto-calculated total**
![POST Order](docs/screenshots/post-order.png)

**POST /orders — Error handling for non-existent dish**
![POST Order Error](docs/screenshots/post-order-error.png)

---

## Development Roadmap

### Level 1 — Foundation ✅
- [x] Basic CRUD for Restaurant, Dish, and Order
- [x] Auto-calculated order total from dish prices
- [x] Status automatically set to `CREATED` on order creation
- [x] DTOs with validation via `@Valid`
- [x] Global exception handler
- [x] Relationships: Restaurant → Dishes, Order → Dishes
- [x] PUT endpoints for Restaurant and Dish
- [x] Separate response DTOs from request DTOs
- [x] Negative price validation on Dish
- [x] Prevent ordering non-existent dishes
- [x] `POST /restaurants/{id}/dishes` and `GET /restaurants/{id}/dishes`
- [x] Docker + Docker Compose with PostgreSQL

### Level 2 — Business Logic
- [ ] `createdAt` and `updatedAt` timestamps on Order
- [ ] `PATCH /orders/{id}/status` with forward-only status transition validation
- [ ] Custom `ResourceNotFoundException`
- [ ] Prevent deletion of restaurants with active orders
- [ ] `GET /orders?status=` filter by status
- [ ] `GET /dishes?category=` filter by category

### Level 3 — Production Ready
- [ ] Spring Security + JWT (login and registration)
- [ ] Protected routes
- [ ] Database migrations with Flyway
- [ ] Dockerfile ✅
- [ ] Docker Compose with app + database services ✅

### Future
- [ ] React frontend consuming the API
