pipeline {
    agent any

    environment {
        ANDROID_HOME = 'C:\\Users\\bui.lan\\AppData\\Local\\Android\\Sdk'
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-21'
        PATH = "${ANDROID_HOME}\\platform-tools;${ANDROID_HOME}\\emulator;${PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/LannBui/SwagLabsAppiumTests.git',
                    branch: 'master'
            }
        }

        stage('Start Emulator') {
            steps {
                bat '''
                    cd %LOCALAPPDATA%\\Android\\Sdk\\emulator
                    emulator -avd Pixel_9a -no-snapshot-load -no-audio -no-window
                '''
                sleep time: 30, unit: 'SECONDS'
            }
        }

        stage('Start Appium Server') {
            steps {
                bat 'start /B appium'
                sleep time: 15, unit: 'SECONDS'
            }
        }

        stage('Run TestNG Tests') {
            steps {
                bat 'mvn clean test -DsuiteXmlFile=full-suite.xml'
            }
        }

        stage('Archive Test Results') {
            steps {
                junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                archiveArtifacts artifacts: 'target/surefire-reports/*.html', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            echo '✅ Test pipeline completed.'
        }
    }
}
