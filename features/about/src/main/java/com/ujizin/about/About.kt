package com.ujizin.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devlucasyuji.about.R
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.ui.Section
import br.com.devlucasyuji.components.ui.animated.AnimatedButtonIcon
import br.com.devlucasyuji.components.ui.button.Button
import br.com.devlucasyuji.components.ui.image.Icons
import br.com.devlucasyuji.components.R as CR

@Composable
fun About(onBackPressed: OnClick) {
    Section(
        trailingIcon = {
            AnimatedButtonIcon(icon = Icons.Back, onClick = onBackPressed)
        },
        title = stringResource(id = R.string.about)
    ) {
        AboutSection(Modifier.weight(1F))
    }
}

@Composable
private fun AboutSection(modifier: Modifier = Modifier) {
    Column(modifier) {
        val context = LocalContext.current
        Image(
            modifier = Modifier
                .padding(top = 32.dp)
                .background(MaterialTheme.colorScheme.primary)
                .aspectRatio(2F),
            contentScale = ContentScale.Fit,
            painter = painterResource(id = CR.drawable.ic_launcher_foreground),
            contentDescription = null
        )

        AboutContent(Modifier.padding(vertical = 24.dp, horizontal = 20.dp))

        Spacer(modifier = Modifier.weight(1F))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            text = stringResource(id = R.string.about_button),
            onClick = context::openProject
        )
    }
}

@Composable
private fun AboutContent(modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = stringResource(id = CR.string.app_name),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            lineHeight = 24.sp,
            text = stringResource(id = R.string.about_description)
        )
    }
}

private fun Context.openProject() {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(getString(R.string.about_project_url))
    }
    startActivity(intent)
}