import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Version{
	const val graphqlVersion = "5.10.0"
	const val graphqlToolsVersion = "5.6.1"
}


plugins {
	id("org.springframework.boot") version "2.1.7.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.10"
	kotlin("plugin.spring") version "1.3.10"
	kotlin("plugin.jpa") version "1.3.10"
}

group = "eu.wojciechzurek"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.graphql-java-kickstart:graphql-java-tools:${Version.graphqlToolsVersion}")
	implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:${Version.graphqlVersion}")
	implementation("com.graphql-java-kickstart:graphiql-spring-boot-starter:${Version.graphqlVersion}")
	implementation("com.graphql-java-kickstart:altair-spring-boot-starter:${Version.graphqlVersion}")
	implementation("com.graphql-java-kickstart:voyager-spring-boot-starter:${Version.graphqlVersion}")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
