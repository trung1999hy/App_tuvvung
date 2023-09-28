package com.tmt.vuaghepchu.ui.game.model

import com.tmt.vuaghepchu.ui.game.BoxTextView

data class ItemViewDataDto(
    var view: BoxTextView,
    var viewAdded: BoxTextView?,
    var isAdded: Boolean,
    var correctValue: String,
    var canShowSuggestion: Boolean
)
