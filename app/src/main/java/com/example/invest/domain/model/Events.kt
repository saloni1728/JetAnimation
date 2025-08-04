package com.example.invest.domain.model

sealed class Events {
    abstract class UIEvent: Events()
}