import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
	val springBootVersion = "3.2.1"
	val springDependenciesVersion = "1.1.4"
	val kotlinVersion = "1.9.22"

	id("org.springframework.boot") version springBootVersion
	id("io.spring.dependency-management") version springDependenciesVersion
	id("org.sonarqube") version "4.0.0.2929"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("plugin.jpa") version kotlinVersion
	kotlin("plugin.allopen") version kotlinVersion
	kotlin("kapt") version kotlinVersion
	jacoco
}


group = "power"
version = "0.1"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

val minioVersion = "8.5.7"
val springdocVersion = "2.3.0"
val kotlinLoggingVersion = "6.0.1"
val mapstructVersion = "1.5.5.Final"
val springMockkVersion = "4.0.2"
val auth0JwtVersion = "4.4.0"

dependencies {
	// Persistence
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.liquibase:liquibase-core")
	implementation("io.minio:minio:$minioVersion")

	// Web
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVersion")
	implementation("com.auth0:java-jwt:$auth0JwtVersion")

	// Other
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.github.oshai:kotlin-logging-jvm:$kotlinLoggingVersion")
	implementation("org.mapstruct:mapstruct:$mapstructVersion")
	kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")

	// Tests
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webflux")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:postgresql")
	testImplementation("org.testcontainers:minio")
	testRuntimeOnly("com.h2database:h2")
	testImplementation("com.ninja-squad:springmockk:$springMockkVersion")

	// Eureka
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

	// Monitoring
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("io.micrometer:micrometer-registry-prometheus")
}

extra["springCloudVersion"] = "2023.0.0"

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

allOpen {
	annotations(
		"jakarta.persistence.Entity",
		"jakarta.persistence.MappedSuperclass",
		"jakarta.persistence.Embeddable"
	)
}

kapt {
	arguments {
		arg("mapstruct.defaultComponentModel", "spring")
	}
}

jacoco {
	toolVersion = "0.8.9"
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
}


