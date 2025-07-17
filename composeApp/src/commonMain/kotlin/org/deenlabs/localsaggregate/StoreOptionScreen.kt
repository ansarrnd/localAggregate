package org.deenlabs.localsaggregate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.deenlabs.localsaggregate.AddProductScreen
import org.deenlabs.localsaggregate.viewmodel.ProductViewModel
import org.deenlabs.localsaggregate.injection.getProductViewModel
import org.deenlabs.localsaggregate.viewmodel.CartViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleSelectionScreen(onNavigateToLogin: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Welcome") })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Please select your role",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OptionCard("Store", Icons.Default.Storefront) { onNavigateToLogin("Store") }
                OptionCard("Customer", Icons.Default.Person) { onNavigateToLogin("Customer") }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OptionCard(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = Modifier.size(150.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = title, modifier = Modifier.size(48.dp))
            Spacer(Modifier.height(16.dp))
            Text(text = title, style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
fun AppPreview() {
    App()
}