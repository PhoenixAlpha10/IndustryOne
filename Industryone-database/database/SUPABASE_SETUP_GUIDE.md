# Connecting IndustryOne to Supabase PostgreSQL

This guide explains how to set up and connect your IndustryOne backend to a cloud PostgreSQL database hosted on **Supabase**.

---

## Step 1: Create a Supabase Project

1. Go to [https://supabase.com](https://supabase.com) and log in.
2. Click **"New project"**.
3. Choose an organization, enter a **Name** (e.g. `industryone`), select your nearest **Region** (e.g., `South Asia (Mumbai)` for India), and set a secure **Database Password**.
   > ⚠️ **Important:** Note down your database password. You will need it in `application.properties`.
4. Click **"Create new project"** and wait ~1–2 minutes for provisioning to complete.

---

## Step 2: Run the Database Schema in Supabase

1. In the Supabase Dashboard, click **"SQL Editor"** (icon on the left navigation bar).
2. Click **"+ New query"**.
3. Open [`supabase_schema.sql`](./supabase_schema.sql) (or [`setup.sql`](./setup.sql)).
4. Copy the entire content and paste it into the Supabase SQL Editor.
5. Click the green **"Run"** button (or press `Ctrl` + `Enter`).
6. You will see `Success. No rows returned` or a preview of the seeded users.
7. Click **"Table Editor"** on the left menu to verify that the following tables exist:
   - `admin_users` (Administrator accounts)
   - `officer_users` (Verification Officer accounts)
   - `applicant_users` (Applicant / Entrepreneur accounts)
   - `auth_tokens` (Session tokens)
   - `login_attempts` (Lockout tracking)
   - `security_alerts` (Security incidents)

---

## Step 3: Get your Supabase Connection String

1. In your Supabase Dashboard, click the **Settings gear icon (⚙️ Project Settings)** at the bottom left.
2. Under **Configuration**, click **"Database"**.
3. Scroll down to the **"Connection string"** section.
4. Select the **"URI"** or **"JDBC"** tab:
   - **Direct Connection:**
     ```
     jdbc:postgresql://db.<YOUR-PROJECT-REF>.supabase.co:5432/postgres?sslmode=require
     ```
     - Username: `postgres`
     - Password: `[YOUR-DATABASE-PASSWORD]`

   - **Connection Pooler (recommended if direct connection gives IPv6 timeout):**
     ```
     jdbc:postgresql://aws-0-<REGION>.pooler.supabase.com:6543/postgres?sslmode=require
     ```
     - Username: `postgres.<YOUR-PROJECT-REF>`
     - Password: `[YOUR-DATABASE-PASSWORD]`

---

## Step 4: Configure Spring Boot Backend

Open `src/main/resources/application.properties` (or refer to `application-supabase.properties`):

```properties
spring.application.name=IndustryOne
server.port=8080

# --- Supabase Database Configuration ---
spring.datasource.url=jdbc:postgresql://db.YOUR_PROJECT_REF.supabase.co:5432/postgres?sslmode=require
spring.datasource.username=postgres
spring.datasource.password=YOUR_DATABASE_PASSWORD

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true

server.error.include-message=never
server.error.include-stacktrace=never
```

Replace `YOUR_PROJECT_REF` and `YOUR_DATABASE_PASSWORD` with your actual values from Supabase.

---

## Step 5: Test the Application

Restart your Spring Boot application:
```bash
mvn clean spring-boot:run
```

You can now log in immediately with any of the seeded accounts:

| Portal | Role | Email | Password |
|---|---|---|---|
| **Admin Console** | `ADMIN` | `admin@industryone.gov.in` | `Admin@12345` |
| **Officer Portal** | `OFFICER` | `officer@industryone.gov.in` | `Officer@12345` |
| **Entrepreneur Portal** | `ENTREPRENEUR` | `entrepreneur@example.com` | `Entrepreneur@12345` |
