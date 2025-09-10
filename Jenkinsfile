pipeline {
    agent {
        label "agent"
    }

    environment {
        ANDROID_HOME = 'C:\\Users\\bui.lan\\AppData\\Local\\Android\\Sdk'
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-21'
        PATH = "${ANDROID_HOME}\\platform-tools;${ANDROID_HOME}\\emulator;${PATH}"
         ANDROID_AVD_HOME = 'C:\\Users\\bui.lan\\.android\\avd'
    }

    stages {
        stage('Checkout') {
            steps {
                // checkout with timeout 10m
                git url: 'https://github.com/LannBui/SwagLabsAppiumTests.git',
                    branch: 'master'
            }
        }

        stage('Run TestNG Tests') {
            steps {
                bat 'mvn clean test -DsuiteXmlFile=testng.xml -Denv=test'
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
            echo 'Test pipeline completed.'
        }
    }
}
