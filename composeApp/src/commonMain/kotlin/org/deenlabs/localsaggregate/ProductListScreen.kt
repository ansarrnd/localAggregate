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
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.deenlabs.localsaggregate.model.GroceryProduct
import org.deenlabs.localsaggregate.utils.formatPrice
import org.deenlabs.localsaggregate.viewmodel.CartViewModel
import org.deenlabs.localsaggregate.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    role: String,
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel,
    onBack: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToAddProduct: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var filterText by remember { mutableStateOf("") }
    val products by productViewModel.products.collectAsState()

    val filteredProducts = remember(products, filterText) {
        if (filterText.isBlank()) {
            products
        } else {
            products.filter { it.name.contains(filterText, ignoreCase = true) }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("$role - Products") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToCart) {
                        BadgedBox(
                            badge = {
                                if (cartViewModel.cartItems.isNotEmpty()) {
                                    Badge { Text("${cartViewModel.cartItems.size}") }
                                }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Shopping Cart")
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            if (role == "Store") {
                Row {
                    FloatingActionButton(onClick = onNavigateToAddProduct) {
                        Icon(Icons.Default.Add, contentDescription = "Add Product")
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            OutlinedTextField(
                value = filterText,
                onValueChange = { filterText = it },
                label = { Text("Search products...") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search Icon")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                singleLine = true
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredProducts, key = { it.id }) { product ->
                    ProductItem(
                        product = product,
                        onAddToCart = {
                            cartViewModel.addToCart(it)
                            scope.launch {
                                snackbarHostState.showSnackbar("${it.name} added to cart")
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductItem(product: GroceryProduct, onAddToCart: (GroceryProduct) -> Unit) {
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = formatPrice(product.price), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                    product.weight?.let { weight ->
                        Text(text = " / $weight", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(start = 4.dp))
                    }
                }
            }
            IconButton(onClick = { onAddToCart(product) }) {
                Icon(
                    imageVector = Icons.Default.AddShoppingCart,
                    contentDescription = "Add to cart"
                )
            }
        }
    }
}