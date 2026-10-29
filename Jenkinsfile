pipeline {
    agent any

    stages {

        stage('Build Service Registry') {
            steps {
                dir('service-registry') {
                    sh 'mvn clean package'
                }
            }
        }

    }
}