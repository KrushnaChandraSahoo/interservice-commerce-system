pipeline {
    agent any

    stages {

        stage('Build Service Registry') {
            steps {
                dir('service-registry') {
                    bat 'mvn clean package'
                }
            }
        }

    }
}