package fus.com.vuatuvung.ui.game.model

import fus.com.vuatuvung.ui.game.BoxView

data class ItemViewData(
    var view: BoxView,
    var viewAdded: BoxView?,
    var isAdded: Boolean,
    var correctValue: String,
    var canShowSuggestion: Boolean
)
