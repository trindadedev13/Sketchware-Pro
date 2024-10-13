package mod.trindadedev.attributes

// This file contains the attributes used in the injection handlers of the ViewPane views.

/*
* Attributes of androidx.cardview.widget.CardView.
* used on { @link ViewPane } for Preview.
*/
object CardAttributes {
    const val CARD_ELEVATION = "cardElevation"
    const val CARD_CORNER_RADIUS = "cardCornerRadius"
    const val CARD_USE_COMPAT_PADDING = "cardUseCompatPadding"
    const val STROKE_COLOR = "strokeColor"
    const val STROKE_WIDTH = "strokeWidth"
}
    
/*
* Attributes of de.hdodenhof.circleimageview.CircleImageView.
* used on { @link ViewPane } for Preview.
*/
object CircleImageViewAttributes {
    const val BORDER_COLOR = "civ_border_color"
    const val CIRCLE_BACKGROUND_COLOR = "civ_circle_background_color"
    const val BORDER_WIDTH = "civ_border_width"
    const val BORDER_OVERLAY = "civ_border_overlay"
}

/*
* Attributes of com.google.android.material.tabs.TabLayout.
* used on { @link ViewPane } for Preview.
*/
object TabAttributes {
    const val TAB_GRAVITY = "tabGravity"
    const val TAB_MODE = "tabMode"
    const val TAB_INDICATOR_HEIGHT = "tabIndicatorHeight"
    const val TAB_INDICATOR_COLOR = "tabIndicatorColor"
    const val TAB_TEXT_COLOR = "tabTextColor"
    const val TAB_SELECTED_TEXT_COLOR = "tabSelectedTextColor"
}

/*
* Attributes of com.google.android.material.button.MaterialButton.
* used on { @link ViewPane } for Preview.
*/
object MaterialButtonAttributes {
    const val CORNER_RADIUS = "cornerRadius"
    const val STROKE_WIDTH = "strokeWidth"
}