package com.example.invest.domain.model

import androidx.lifecycle.ViewModel

abstract class EventHandler<out T: Events.UIEvent> : ViewModel() {

    abstract fun handleEvent(event: Events.UIEvent)
}