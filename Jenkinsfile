pipeline {
    agent any
        tools {
            jdk 'JDK_17'
        maven 'maven_microservice'
    }
    stages {

        stage('Build Service Registry') {
            steps {
                dir('service-registry') {
                    sh 'java -version'
                    sh 'mvn clean package'
                }
            }
        }

    }
}