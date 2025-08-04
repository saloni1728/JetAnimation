package com.example.invest.presentation.custom

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.PointerInputModifierNode
import androidx.compose.ui.unit.IntSize

class CustomClickableModifierNode(
    var onClick: () -> Unit,
    var isDebounceEnable: Boolean = true,
    var debounceTime: Long = 1000L
): Modifier.Node(), PointerInputModifierNode {
    private var lastClickTime: Long = 0
    override fun onCancelPointerInput() {
        lastClickTime = System.currentTimeMillis()
    }

    override fun onPointerEvent(
        pointerEvent: PointerEvent,
        pass: PointerEventPass,
        bounds: IntSize
    ) {
        val currentTime = System.currentTimeMillis()
        if (pointerEvent.changes.any { it.pressed }) {
            if (isDebounceEnable && currentTime - lastClickTime > debounceTime) {
                lastClickTime = currentTime
                onClick()
            } else if (!isDebounceEnable) {
                onClick()
            }
        }
    }
}

class CustomClickableModifier(
    val onClick: () -> Unit,
    val isDebounceEnable: Boolean = true,
    val debounceTime: Long = 1000L
): ModifierNodeElement<CustomClickableModifierNode>() {
    override fun create(): CustomClickableModifierNode = CustomClickableModifierNode(
        onClick = onClick,
        isDebounceEnable = isDebounceEnable,
        debounceTime = debounceTime
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CustomClickableModifier) return false
        if (onClick != other.onClick) return false

        return true
    }

    override fun hashCode(): Int {
        var result = onClick.hashCode()
        result = 31 * result + isDebounceEnable.hashCode()
        result = 31 * result + debounceTime.hashCode()
        return result
    }

    override fun update(node: CustomClickableModifierNode) {
        node.onClick = onClick
        node.isDebounceEnable = isDebounceEnable
        node.debounceTime = debounceTime
    }
}

fun Modifier.customClickable(
    onClick: () -> Unit,
    isDebounceEnable: Boolean = true,
    debounceTime: Long = 1000L
) = this.then(CustomClickableModifier(onClick = onClick, isDebounceEnable = isDebounceEnable, debounceTime = debounceTime))