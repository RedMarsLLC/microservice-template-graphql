# 🛰️ microservice-template-graphql

**A production-ready Spring Boot + GraphQL microservice template from RedMars Labs**  
Built for secure, modular backend development with baked-in authentication, GraphiQL support, and extendable architecture.

---

## 🚀 What Is This?

`microservice-template-graphql` is a boilerplate Spring Boot application preconfigured with:

- ✅ Spring Boot 3.x
- ✅ Spring Security with in-memory user authentication
- ✅ BCrypt password encoding
- ✅ GraphQL support using Spring for GraphQL
- ✅ GraphiQL playground interface
- ✅ Clean, modular project structure
- ✅ Standard security best practices

Built and maintained by **RedMars Labs** to help developers get started building GraphQL-based Java microservices quickly and securely.

---

## 🛠️ Technologies

| Tech               | Description                      |
|--------------------|----------------------------------|
| Java 24+           | Core language                    |
| Spring Boot 3.x    | Application framework            |
| Spring Security    | Secure endpoint protection       |
| Spring for GraphQL | GraphQL support (schema-first)   |
| GraphiQL UI        | Interactive playground           |

---

## 🔐 Default Credentials

The application includes in-memory user authentication:

Username: admin
Password: superSecretPassword


> Password is stored using **BCrypt** and can be modified in `SecurityConfig.java`.

---

## 🧰 Running the App

### Prerequisites

- Java 24+
- Maven 3.8+

### Run locally:
```
./mvnw spring-boot:run
```

Then open:
```
http://localhost:8080/graphiql
```

### 📂 Project Structure
```
src/
├── main/
│   ├── java/com/redmars/microservice_template_graphql/
│   │   ├── controller/        # GraphQL resolvers
│   │   ├── model/             # Domain models
│   │   ├── config/            # Security config
│   │   └── MicroserviceTemplateGraphqlApplication.java
│   └── resources/
│       ├── graphql/           # .graphqls schema files
│       └── application.properties
```

### 🧪 Example Queries
```
query {
  hello
}

query {
  userById(id: "1") {
    fname
    email
  }
}
```

## 🧾 License

This project is licensed under the MIT License.  
See the [LICENSE](./LICENSE) file for details.
