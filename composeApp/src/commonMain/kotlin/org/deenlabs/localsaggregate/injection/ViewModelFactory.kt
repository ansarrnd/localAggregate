package org.deenlabs.localsaggregate.injection

import androidx.compose.runtime.Composable
import org.deenlabs.localsaggregate.viewmodel.ProductViewModel

@Composable
expect fun getProductViewModel(): ProductViewModel