# IndustryOne | National Single Window Industrial Clearance System

An enterprise-grade, digital single-window portal designed to streamline, automate, and accelerate industrial licensing, statutory environmental NOCs, factory approvals, and departmental clearances for industrial enterprises across India.

---

## Architecture Overview

IndustryOne is structured with a decoupled, high-performance architecture:

- **Frontend**: Vanilla HTML5, modular CSS3 (`login.css`), responsive Tailwind CSS utilities, and vanilla JavaScript with strict GIGW / W3C WCAG accessibility standards.
- **Backend API**: Java 17+, Spring Boot 3, Spring Data JPA, Hibernate, HikariCP connection pooling, and BCrypt cryptographic password hashing.
- **Database**: PostgreSQL hosted on cloud Supabase with multi-role partitioned user tables, automated session token management, brute-force lockout tracking, and administrative incident auditing.

---

## Repository Structure

```text
Industry-Approval-System/
├── .gitignore                   # Git exclusion rules (build artifacts, caches, secrets)
├── README.md                    # Project documentation & execution guide
├── login.css                    # Unified national portal stylesheet & color design system
│
├── Frontend Portals & Pages:
│   ├── home.html                # Central National Single Window Clearance Gateway
│   ├── About.html               # Mandate, governance, statutory charter, and policies
│   ├── Helpdesk.html            # Grievance redressal desk, ticket filing & tracking
│   ├── Helpdesk.js              # Helpdesk interactivity, FAQ filtering & ticket submission
│   ├── login.html               # Entrepreneur & Applicant statutory login portal
│   ├── login.js                 # Login controller, visual/audio captcha & session handling
│   ├── Applicant.html           # New enterprise & applicant registration gateway
│   ├── applicant.js             # Enterprise registration validator & API integration
│   ├── applicant-dashboard.html # Applicant portal for NOC tracking & certificate vault
│   ├── applicant-dashboard.js   # Clearance tracker, vault viewer & profile management
│   ├── Officer.html             # Verification Officer & Scrutiny registration gateway
│   ├── officer.js               # Officer credential validator & submission handler
│   ├── AdminLogin.html          # Directorate & Department Head administrative login
│   ├── admin-login.js           # Admin authentication with role authorization
│   ├── AdminDashboard.html      # Central administration oversight & security dashboard
│   └── admin-dashboard.js      # Live KPI metrics, clearance dispatch & security alerts
│
└── Industryone-database/        # Spring Boot backend application
    ├── pom.xml                  # Maven dependencies & build specification
    ├── mvnw / mvnw.cmd          # Cross-platform Maven wrapper scripts
    ├── database/
    │   ├── setup.sql            # Definitive PostgreSQL / Supabase DDL schema & seed data
    │   ├── supabase_schema.sql  # Annotated cloud schema with index optimizations
    │   └── SUPABASE_SETUP_GUIDE.md # Step-by-step database provisioning guide
    └── src/
        ├── main/java/com/industryone/
        │   ├── IndustryOneApplication.java  # Main application entrypoint
        │   ├── config/                      # CORS & security configurations
        │   ├── controller/                  # REST Controllers (/api/login, /api/admin/alerts)
        │   ├── model/                       # Data entities & DTO records
        │   ├── repository/                  # Spring Data JPA repositories
        │   └── service/                     # Authentication & security business logic
        └── main/resources/
            ├── application.properties         # Active runtime properties (with env overrides)
            └── application.properties.example # Sanitized template for environment configuration
```

---

## User Roles & Credentials

The database comes pre-seeded with three dedicated role accounts:

| Role                     | Portal Page       | Default Username             | Default Password     |
| :----------------------- | :---------------- | :--------------------------- | :------------------- |
| **Directorate Admin**    | `AdminLogin.html` | `admin@industryone.gov.in`   | `Admin@12345`        |
| **Scrutiny Officer**     | `AdminLogin.html` | `officer@industryone.gov.in` | `Officer@12345`      |
| **Enterprise Applicant** | `login.html`      | `entrepreneur@example.com`   | `Entrepreneur@12345` |

---

## REST API Endpoints

All backend APIs are exposed under `/api`:

| Method | Endpoint                         | Description                                                 |
| :----- | :------------------------------- | :---------------------------------------------------------- |
| `POST` | `/api/login`                     | Multi-role authentication with BCrypt & brute-force lockout |
| `POST` | `/api/register`                  | Enterprise applicant self-registration                      |
| `POST` | `/api/logout`                    | Invalidate active authentication session                    |
| `GET`  | `/api/verify`                    | Verify authentication token validity & active role          |
| `GET`  | `/api/admin/alerts`              | Fetch recent security lockout & intrusion alerts            |
| `POST` | `/api/admin/alerts/{id}/resolve` | Mark an alert as resolved by an administrator               |

---

## Getting Started

### 1. Backend Service (Spring Boot)

#### Prerequisites:

- Java JDK 17 or higher
- Internet access for Maven dependencies and Supabase cloud database

#### Running with Maven:

```powershell
cd Industryone-database
./mvnw spring-boot:run
```

The server starts on port `8080`.

#### Environment Variable Overrides:

You can customize database credentials without modifying code:

```powershell
$env:DB_URL = "jdbc:postgresql://your-db-host:5432/postgres?sslmode=require"
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "your_secure_password"
./mvnw spring-boot:run
```

### 2. Frontend Interface

The frontend requires no build steps or heavy node runtimes. Serve the HTML files using any static server or open them directly in your browser:

```powershell
# Using Python
python -m http.server 3000

# Using Node / npx
npx serve .

# Or open home.html directly in your browser
Start-Process home.html
```

---

## Security & Compliance Features

- **BCrypt Hashing**: All passwords securely hashed using `BCryptPasswordEncoder` with strength factor 10.
- **Statutory Rate Limiting**: Automatic account lockout for 15 minutes upon 3 consecutive failed login attempts per role.
- **Incident Audit Trail**: Every security violation is logged to `security_alerts` for supervisory review.
- **Accessibility & GIGW**: Font sizing scaling controls, accessible SVG contrast, and visual/audio Captcha verification.
