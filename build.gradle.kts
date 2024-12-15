// build.gradle.kts en el nivel de proyecto (Project: EvaluadorDeFatiga)
// Archivo de compilación principal donde puedes agregar configuraciones comunes a todos los subproyectos/módulos.

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false

}
