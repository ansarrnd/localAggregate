package org.deenlabs.localsaggregate.injection

import org.deenlabs.localsaggregate.viewmodel.StoreViewModel

private val storeViewModel = StoreViewModel()

fun getStoreViewModel(): StoreViewModel = storeViewModel