// Declarative pipeline for the Traya Mobile Automation framework.
//
// Requires a macOS Jenkins agent with: Java 21, Maven, Android SDK + an emulator AVD already
// created, Node.js + the Appium CLI (npm install -g appium) with the uiautomator2/xcuitest
// drivers installed, and Xcode (only needed for the iOS suites). See docs/jenkins-ci-setup.md
// for the one-time agent/job/webhook setup this file assumes.

pipeline {

    // Change this label to whatever you assign your macOS Android agent in Jenkins.
    agent any

    parameters {
        choice(
            name: 'SUITE_XML',
            choices: ['testng-regression.xml', 'testng-android.xml', 'testng-ios.xml', 'testng-parallel.xml', 'testng.xml'],
            description: 'Which TestNG suite file to run (mirrors -DsuiteXmlFile=)'
        )
        choice(
            name: 'PLATFORM_OVERRIDE',
            choices: ['', 'Android', 'iOS'],
            description: 'Optional -Dplatform= override. Leave blank to let the chosen suite\'s own <parameter> decide.'
        )
        string(
            name: 'AVD_NAME',
            defaultValue: 'Pixel_10_Pro',
            description: 'Android emulator AVD to boot for this run (only used if no emulator is already running)'
        )
    }

    triggers {
        // Requires the GitHub plugin and a webhook on the repo pointing at
        // <jenkins-url>/github-webhook/ - see docs/jenkins-ci-setup.md.
        githubPush()
    }

    options {
        timeout(time: 30, unit: 'MINUTES')
        disableConcurrentBuilds() // one emulator/Appium instance per agent - don't overlap runs
        timestamps()
    }

   environment {
    ANDROID_HOME = "${env.HOME}/Library/Android/sdk"
    PATH = "/opt/homebrew/bin:${env.ANDROID_HOME}/platform-tools:${env.ANDROID_HOME}/emulator:${env.PATH}"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        // stage('Verify Toolchain') {
        //     steps {
        //         sh '''
        //             set -e
        //             java -version
        //             mvn -version
        //             adb version
        //             appium --version
        //         '''
        //     }
        // }
        stage('Verify Toolchain') {
    steps {
        sh '''
            set -e

            echo "===== PATH ====="
            echo $PATH

            echo "===== WHICH ====="
            which java || true
            which mvn || true
            which adb || true
            which node || true
            which npm || true
            which appium || true

            echo "===== VERSIONS ====="
            java -version
            mvn -version
            adb version
            node -v
            npm -v
            appium --version
        '''
    }
}

        stage('Boot Android Emulator') {
            when {
                expression { params.SUITE_XML != 'testng-ios.xml' }
            }
            options {
                timeout(time: 8, unit: 'MINUTES')
            }
            // steps {
            //     sh '''
            //         set -e
            //         if adb devices | grep -q "^emulator-"; then
            //             echo "Emulator already running, reusing it"
            //         else
            //             nohup emulator -avd "${AVD_NAME}" -no-snapshot -no-window -no-audio -gpu swiftshader_indirect > emulator.log 2>&1 &
            //             adb wait-for-device

            //             BOOTED=""
            //             for i in $(seq 1 60); do
            //                 BOOTED=$(adb shell getprop sys.boot_completed 2>/dev/null | tr -d '\\r')
            //                 if [ "$BOOTED" = "1" ]; then break; fi
            //                 sleep 5
            //             done

            //             if [ "$BOOTED" != "1" ]; then
            //                 echo "Emulator failed to reach boot_completed within timeout"
            //                 cat emulator.log || true
            //                 exit 1
            //             fi
            //         fi
            //         adb devices
            //     '''
            // }
            steps {
                sh '''
                    set -e

                        echo "Checking for a running emulator..."

                    if adb devices | grep -q "^emulator-"; then
                        echo "Emulator is already running."
                    else
                        echo "No Android emulator is running."
                        echo "Please start the emulator manually from Android Studio and rebuild."
                        exit 1
                    fi

                    adb devices
             '''
            }
        }

        stage('Start Appium Server') {
            steps {
                sh '''
                    set -e
                    nohup appium --log-level info > appium.log 2>&1 &
                    echo $! > appium.pid

                    READY=""
                    for i in $(seq 1 20); do
                        if curl -sf http://127.0.0.1:4723/status > /dev/null; then
                            READY="1"
                            break
                        fi
                        sleep 2
                    done

                    if [ "$READY" != "1" ]; then
                        echo "Appium server did not become ready"
                        cat appium.log || true
                        exit 1
                    fi
                '''
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    def platformFlag = params.PLATFORM_OVERRIDE?.trim() ? "-Dplatform=${params.PLATFORM_OVERRIDE}" : ''
                    sh "mvn -B test -DsuiteXmlFile=${params.SUITE_XML} ${platformFlag}"
                }
            }
        }
    }

    post {
        always {
            sh '''
                if [ -f appium.pid ]; then
                    kill "$(cat appium.pid)" 2>/dev/null || true
                fi
        #        adb emu kill 2>/dev/null || true
            '''
            archiveArtifacts artifacts: 'test-output/reports/*.pdf, test-output/screenshots/**/*.png, test-output/logs/*.log, appium.log, emulator.log',
                              allowEmptyArchive: true,
                              fingerprint: true
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
        }
        failure {
            echo 'Build failed - the PDF report and failure screenshots above have the full diagnostic detail.'
        }
    }
}
