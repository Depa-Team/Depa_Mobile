package com.depa.Adapters

import Beans.Flats

interface FlatAdapterListener {
    fun onActionsItemClick(flat: Flats)
    fun onActionsItemCall(flat: Flats)
}