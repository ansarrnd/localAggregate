package org.deenlabs.localsaggregate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.deenlabs.localsaggregate.model.GroceryProduct

// Sample data for demonstration
private val sampleProducts = listOf(
    GroceryProduct("1", "Fresh Apples", 2.99, ""),
    GroceryProduct("2", "Organic Bananas", 1.50, ""),
    GroceryProduct("3", "Whole Wheat Bread", 3.49, ""),
    GroceryProduct("4", "Free-Range Eggs", 4.99, ""),
    GroceryProduct("5", "Milk (1 Gallon)", 3.79, ""),
    GroceryProduct("6", "Cheddar Cheese", 5.25, ""),
    GroceryProduct("7", "Chicken Breast", 9.99, ""),
    GroceryProduct("8", "Tomatoes", 2.20, ""),
    GroceryProduct("9", "Spinach", 2.00, ""),
    GroceryProduct("10", "Pasta", 1.29, "")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(role: String, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("$role - Products") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sampleProducts) { product ->
                ProductItem(product)
            }
        }
    }
}

@Composable
private fun ProductItem(product: GroceryProduct) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Fastfood,
                contentDescription = product.name,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "$${"%.2f".format(product.price)}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
            }
        }
    }
}