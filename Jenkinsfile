pipeline {
    agent any
        tools {
            jdk 'JDK17'
        maven 'Maven-3.9'
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