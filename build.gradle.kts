plugins {
    java
    kotlin("jvm") version "1.6.10"
}

repositories {
    mavenCentral()
    google()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    val a1 = create("temp-file") {
        description = "This task will crete temp file."
        group = "otus"

        doLast {
            File("temp-file.txt").also {
                it.createNewFile()
                it.writeText("This is temp file!")
            }
            println("File is created!")
        }
    }
    withType()
    test {
        useJUnitPlatform()
    }

//    named<Test>("test") {
//
//    }

    val customTask = create<CustomTask>("custom-task")

    val jar = withType<org.gradle.jvm.tasks.Jar>()

    register<Jar>("fat-jar") {
        dependsOn(jar)

        manifest {
            attributes(
                "Main-Class" to "org.sample.MyMain"
            )
        }
        from(
            configurations.compileClasspath.get().map {
                if (it.isDirectory) it else zipTree(it)
            }
        )
    }
}

val gsonVersion: String by project
val myCustomVersion = property("my_custom_version1", "-1")


println(myCustomVersion)

dependencies {
    implementation("com.google.code.gson:gson:$gsonVersion")

    testImplementation(kotlin("test-junit"))
}

group = "org.example"
version = "0.1-SNAPSHOT"


fun property(key: String, default: String): String {
    return project.properties.getOrDefault(key, default).toString()
}
