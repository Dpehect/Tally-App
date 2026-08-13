package com.softbridge.tallymobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.softbridge.tallymobile.ui.TallyApp
import com.softbridge.tallymobile.ui.theme.TallyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { TallyTheme { TallyApp() } }
    }
}
