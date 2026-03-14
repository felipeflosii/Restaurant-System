# RestaurantSystem

REST API for restaurant order management, built with Java and Spring Boot. The project simulates a real backend used in delivery or internal management systems, applying clean architecture, relational data modeling, and layered organization.

---

## Tech Stack

| Technology | Details |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.0.3 |
| Persistence | Spring Data JPA + Hibernate |
| Database | H2 (development) → PostgreSQL (production) |
| Migrations | Flyway (planned — Level 3) |
| Validation | Jakarta Validation (Bean Validation) |
| Security | Spring Security + JWT (planned — Level 3) |
| Boilerplate | Lombok |
| Build Tool | Maven 3.9.12 |
| Containerization | Docker + Docker Compose (planned — Level 3) |

---

## Project Structure

```
src/main/java/br/com/flosi/restaurant/
├── controllers/        REST controllers (HTTP layer)
├── services/           Business logic layer
├── repositories/       Spring Data JPA interfaces
├── models/             JPA entities and enums
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
| MenuItem | A dish offered by a restaurant with name, description, category, and price |
| Order | A customer order linked to menu items, with a total price and lifecycle status |

### Enums

| Enum | Values |
|---|---|
| MenuCategory | `APPETIZER`, `SOUP`, `SALAD`, `MAIN_COURSE`, `PIZZA`, `PASTA`, `SEAFOOD`, `GRILL`, `VEGETARIAN`, `VEGAN`, `DESSERT`, `BEVERAGE`, `COMBO`, `SPECIAL`, and more |
| OrderStatus | `CREATED` → `CONFIRMED` → `PREPARING` → `READY` → `OUT_FOR_DELIVERY` → `DELIVERED` / `CANCELLED` / `PAID` |

---

## API Endpoints

### Restaurants — `/restaurants`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/restaurants` | List all restaurants |
| GET | `/restaurants/{id}` | Get a restaurant by ID |
| POST | `/restaurants` | Create a new restaurant |
| DELETE | `/restaurants/{id}` | Delete a restaurant |

### Menu Items — `/menu-items`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/menu-items` | List all menu items |
| GET | `/menu-items/{id}` | Get a menu item by ID |
| POST | `/menu-items` | Create a new menu item |
| DELETE | `/menu-items/{id}` | Delete a menu item |

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

- Java 21 or higher
- Maven 3.9+ (or use the included `mvnw` wrapper)

### Run Locally

```bash
git clone https://github.com/felipeflosii/RestaurantSystem.git
cd RestaurantSystem
./mvnw spring-boot:run
```

The API starts on `http://localhost:8080`. The H2 in-memory database is pre-configured — no external database setup required for development.

### Example Requests

**Create a menu item:**
```json
POST /menu-items

{
  "name": "Margherita Pizza",
  "description": "Classic tomato, mozzarella, and basil.",
  "category": "PIZZA",
  "price": 42.90
}
```

**Create an order:**
```json
POST /orders

{
  "name": "John Doe",
  "menuItemIds": [1, 3, 5]
}
```

---

## Development Roadmap

### Level 1 — Foundation *(in progress)*
- [x] Basic CRUD for Restaurant, MenuItem, and Order
- [x] Auto-calculated order total from menu item prices
- [x] Status automatically set to `CREATED` on order creation
- [x] DTOs with validation via `@Valid`
- [x] Global exception handler
- [ ] Relationships: Restaurant → MenuItems, Order → MenuItems
- [ ] PUT endpoints for updating Restaurant and MenuItem
- [ ] Separate response DTOs from request DTOs
- [ ] Negative price validation on MenuItem
- [ ] Prevent ordering non-existent items
- [ ] `POST /restaurants/{id}/menu-items` and `GET /restaurants/{id}/menu-items`

### Level 2 — Business Logic
- [ ] `createdAt` and `updatedAt` timestamps on Order
- [ ] `PATCH /orders/{id}/status` with forward-only status transition validation
- [ ] Custom `ResourceNotFoundException`
- [ ] Prevent deletion of restaurants with active orders
- [ ] `GET /orders?status=` filter by status
- [ ] `GET /menu-items?category=` filter by category

### Level 3 — Production Ready
- [ ] Spring Security + JWT (login and registration)
- [ ] Protected routes
- [ ] Swap H2 for PostgreSQL
- [ ] Database migrations with Flyway
- [ ] Dockerfile
- [ ] Docker Compose with app + database services

### Future
- [ ] React frontend consuming the API