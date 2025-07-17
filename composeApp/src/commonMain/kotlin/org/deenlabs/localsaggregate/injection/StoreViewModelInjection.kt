package org.deenlabs.localsaggregate.injection

import androidx.compose.runtime.Composable
import org.deenlabs.localsaggregate.viewmodel.StoreViewModel

@Composable
expect fun getStoreViewModel(): StoreViewModel