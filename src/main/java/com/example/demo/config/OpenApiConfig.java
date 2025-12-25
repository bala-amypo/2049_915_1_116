# ===============================
# SERVER CONFIGURATION
# ===============================
server.port=8080
server.servlet.context-path=/demo

# ===============================
# SPRING DATASOURCE (MySQL)
# ===============================
spring.datasource.url=jdbc:mysql://localhost:3306/demo_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password_here
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# ===============================
# JPA / HIBERNATE CONFIGURATION
# ===============================
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.id.new_generator_mappings=true

# ===============================
# LOGGING CONFIGURATION
# ===============================
logging.level.root=INFO
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

# ===============================
# OPTIONAL: H2 CONSOLE (if you use H2)
# ===============================
# spring.h2.console.enabled=true
# spring.h2.console.path=/h2-console
 change the port as per your server
                .servers(List.of(
                        new Server().url("https://9064.408procr.amypo.ai//")
                ));
        }
}