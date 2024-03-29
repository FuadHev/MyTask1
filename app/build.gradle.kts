plugins {
    id("com.android.application")
}


android {
    namespace = "com.fuadhev.mytayqatask1"
    compileSdk = 34

    buildFeatures{
        viewBinding=true
    }

    defaultConfig {
        applicationId = "com.fuadhev.mytayqatask1"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.6")
    implementation("androidx.navigation:navigation-ui:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("org.greenrobot:eventbus:3.3.1")


    implementation ("io.objectbox:objectbox-android:3.7.1")
    annotationProcessor ("io.objectbox:objectbox-processor:3.7.1")
    //retrofit
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Using JCenter
    implementation ("eu.davidea:flexible-adapter:5.1.0")
    implementation ("eu.davidea:flexible-adapter-ui:1.0.0")
    implementation ("eu.davidea:flexible-adapter-livedata:1.0.0-b3")
    // From 1.0.0-b3, library is compiled with data binding v2
    implementation ("eu.davidea:flexible-adapter-databinding:1.0.0")
}

apply(plugin = "io.objectbox")