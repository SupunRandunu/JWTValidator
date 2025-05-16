# 🔐 JWT Validator (Java)

A minimal Java application that validates a JWT token using a **JWKS endpoint**.

---

## 📦 Features

- ✅ Validates JWT signature using a public JWKS endpoint
- ⏱️ Checks standard claims: `exp`, `nbf`, `iat`
- 🧾 Prints full JWT headers and claim set
- ⚙️ Configuration via simple `config.properties` file
- 🔧 Lightweight, standalone, Maven-based project

---

## 🗂 Project Structure

```
jwt-validator/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/sample/jwtvalidator/
│   │   │       ├── JwtValidatorApp.java
│   │   │       ├── config/
│   │   │       │   └── ConfigLoader.java
│   │   │       ├── model/
│   │   │       │   └── ValidationResult.java
│   │   │       └── service/
│   │   │           └── JwtValidator.java
│   │   └── resources/
│   │       └── config.properties
```

---

## ⚙️ Configuration

Edit the file `src/main/resources/config.properties` with your JWT and JWKS endpoint:

```properties
jwks.url=https://your-domain.com/.well-known/jwks.json
jwt=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9...
```

- `jwks.url` — Public JWKS endpoint from your identity provider.
- `jwt` — The JWT token to validate.

---

## 🚀 How to Build and Run

### 📌 Prerequisites

- Java 11 or later
- Maven 3.x

### 🛠️ Build

```bash
mvn clean install
```

### ▶️ Run the App

```bash
mvn exec:java -Dexec.mainClass=org.sample.jwtvalidator.JwtValidatorApp
```

---

## 📝 Example Output

```
🔐 JWT Header: {"alg":"RS256","kid":"abc123",...}
✅ Signature valid: true

📋 Claims:
  ➤ Subject: user123
  ➤ Issuer : https://example.com
  ➤ Audience: [my-app]
  ➤ Expiration: Fri May 17 11:23:00 UTC 2025
  ➤ Issued At : Fri May 17 10:23:00 UTC 2025
  ➤ Not Before: Fri May 17 10:00:00 UTC 2025
✅ Token is within valid time range
```

---

All dependencies are declared in `pom.xml`.

---

## 📄 License

This project is licensed under the MIT License.

---

## 📁 Download

📥 [Download the full project as ZIP](https://github.com/SupunRandunu/JWTValidator/archive/refs/heads/master.zip)

---
