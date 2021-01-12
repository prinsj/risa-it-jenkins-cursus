#!groovy
pipeline {
    agent {
        label 'master'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
    }

    stages {
        stage('Initialisation') {
            steps {
            }
        }

        stage('Compile') {
            steps {
                script {
                    if (isUnix()) {
                        sh "mvn compile"
                    } else {
                        bat 'mvn compile'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn verify'
                    } else {
                        bat 'mvn verify'
                    }
                }
            }
        }

        stage('Package') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn package'
                    } else {
                        bat 'mvn package'
                    }
                }
            }
        }
    }
}
