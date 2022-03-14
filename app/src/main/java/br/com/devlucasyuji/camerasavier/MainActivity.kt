package br.com.devlucasyuji.camerasavier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.devlucasyuji.camerasavier.ui.theme.CameraSavierTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameraSavierTheme {

            }
        }
    }
}
