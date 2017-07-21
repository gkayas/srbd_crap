/*
 * Copyright (C) 2016 Samsung Electronics
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY APPLE INC. AND ITS CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL APPLE INC. OR ITS CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * @file    ewk_settings_product.h
 * @brief   Describes the settings API.
 *
 * @note The ewk_settings is for setting the preference of specific ewk_view.
 * We can get the ewk_settings from ewk_view using ewk_view_settings_get() API.
 */

#ifndef ewk_settings_product_h
#define ewk_settings_product_h

#ifdef __cplusplus
extern "C" {
#endif

enum _Ewk_List_Style_Position {
    EWK_LIST_STYLE_POSITION_OUTSIDE, /**< Default WebKit value. */
    EWK_LIST_STYLE_POSITION_INSIDE
};
typedef enum _Ewk_List_Style_Position Ewk_List_Style_Position;

/**
 * Requests setting of force zoom.
 *
 * @param settings settings object to enable force zoom
 * @param enable to force zoom
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_force_zoom_set(Ewk_Settings *settings, Eina_Bool enable);

/**
 * Returns the force zoom status.
 *
 * @param settings settings object to enable force zoom
 *
 * @return @c EINA_TRUE if enable force zoom or @c EINA_FALSE.
 */
EXPORT_API Eina_Bool ewk_settings_force_zoom_get(const Ewk_Settings *settings);

/**
 * Requests to set the default font size.
 *
 * @param settings settings object to set the default font size
 * @param size a new default font size to set
 *
 * @return @c EINA_TRUE on success @c EINA_FALSE otherwise
 */
EXPORT_API Eina_Bool ewk_settings_font_default_size_set(Ewk_Settings *settings, int size);

/**
 * Returns the default font size.
 *
 * @param settings settings object to set the default font size
 *
 * @return @c default font size.
 */
EXPORT_API int ewk_settings_font_default_size_get(const Ewk_Settings *settings);

/**
 * Requests enables/disables if the scripts can open the new windows.
 *
 * @param settings settings object to set if the scripts can open the new windows
 * @param allow @c EINA_TRUE if the scripts can open the new windows
 *        @c EINA_FALSE if not
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure (scripts are disabled)
 */
EXPORT_API Eina_Bool ewk_settings_scripts_window_open_set(Ewk_Settings *settings, Eina_Bool allow);

/**
 * Returns enables/disables if the scripts can open the new windows.
 *
 * @param settings settings object to set if the scripts can open the new windows
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure (scripts are disabled)
 */
EXPORT_API Eina_Bool ewk_settings_scripts_window_open_get(const Ewk_Settings *settings);

/**
 * Returns default text encoding name.
 *
 * @param settings settings object to query default text encoding nae
 *
 * @return default text encoding name
 */
EXPORT_API const char* ewk_settings_default_encoding_get(const Ewk_Settings *settings);

/**
 * Requests to set default text encoding name.
 *
 * @param settings settings object to set default text encoding name
 * @param encoding default text encoding name
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_default_encoding_set(Ewk_Settings *settings, const char* encoding);

/**
 * \enum    _Ewk_Editable_Link_Behavior
 *
 * @brief   Editable link behavior mode (Must remain in sync with WKEditableLinkBehavior)
 */
enum _Ewk_Editable_Link_Behavior {
    EWK_EDITABLE_LINK_BEHAVIOR_DEFAULT,
    EWK_EDITABLE_LINK_BEHAVIOR_ALWAYS_LIVE,
    EWK_EDITABLE_LINK_BEHAVIOR_ONLY_LIVE_WITH_SHIFTKEY,
    EWK_EDITABLE_LINK_BEHAVIOR_LIVE_WHEN_NOT_FOCUSED,
    EWK_EDITABLE_LINK_BEHAVIOR_NEVER_LIVE
};
typedef enum _Ewk_Editable_Link_Behavior Ewk_Editable_Link_Behavior;

/**
 * Requests to set editable link behavior.
 *
 * @param settings settings object to set editable link behavior
 * @param behavior editable link behaviro
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_editable_link_behavior_set(Ewk_Settings *settings, Ewk_Editable_Link_Behavior behavior);

/**
 * Requests to set the load remote images enable/disable
 *
 * @param settings settings object to set load remote images
 *
 * @param loadRemoteImages @c EINA_TRUE to enable the load remote images
 *        @c EINA_FALSE to disable
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_load_remote_images_set(Ewk_Settings *settings, Eina_Bool loadRemoteImages);

/**
 * Returns enable/disable the load remote images
 *
 * @param settings settings object to get editable link behavior
 *
 * @return @c EINA_TRUE on enable or @c EINA_FALSE on disable
 */
EXPORT_API Eina_Bool ewk_settings_load_remote_images_get(const Ewk_Settings *settings);

/**
 * Returns uses encoding detector.
 *
 * @deprecated Deprecated since Tizen 3.0.
 *
 * @param settings settings object to query uses encoding detector
 *
 * @see ewk_settings_encoding_detector_enabled_get
 *
 * @return uses encoding detector
 */
EINA_DEPRECATED EXPORT_API Eina_Bool ewk_settings_uses_encoding_detector_get(const Ewk_Settings *settings);

/**
 * Returns if password form autofill is enabled or disabled.
 *
 * @param setting setting object to get password form autofill
 *
 * @return @c EINA_TRUE if password form autofill is enabled
 *         @c EINA_FALSE if password form autofill is disabled
 */
EXPORT_API Eina_Bool ewk_settings_autofill_password_form_enabled_get(Ewk_Settings* settings);

/**
 * Returns if form candidate data for autofill is enabled or disabled.
 *
 * @param setting setting object to get form candidate data for autofill
 *
 * @return @c EINA_TRUE if form candidate data for autofill is enabled
 *         @c EINA_FALSE if form candidate data for autofill is disabled
 */
EXPORT_API Eina_Bool ewk_settings_form_candidate_data_enabled_get(Ewk_Settings* settings);

/**
 * Returns whether the autofill_text feature is enabled.
 *
 * @param settings settings object to query whether autofill_text feature is enabled
 *
 * @return @c EINA_TRUE if the autofill_text feature is enabled
 *         @c EINA_FALSE if not or on failure
 */
EXPORT_API Eina_Bool ewk_settings_form_profile_data_enabled_get(const Ewk_Settings *settings);

/**
 * Returns whether text selection is cleared when webview lose focus or not.
 *
 * @param settings setting object to get whether text selection is cleared when webview lose focus or not
 *
 * @return @c EINA_TRUE if text selection is cleared when webview lose focus
 *         @c EINA_FALSE if not or on failure
 */
EXPORT_API Eina_Bool ewk_settings_clear_text_selection_automatically_get(const Ewk_Settings* settings);

/**
 * Requests enables/disables to clear text selection when webview lose focus
 *
 * @param settings setting object to set to clear text selection when webview lose focus
 * @param enable @c EINA_TRUE to clear text selection when webview lose focus
 *        @c EINA_FALSE to disable
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_clear_text_selection_automatically_set(Ewk_Settings* settings, Eina_Bool enable);

/**
 * Requests for drawing layer borders.
 *
 * @param settings settings object to drawing layer borders.
 * @param enable EINA_TRUE to draw layer borders.
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */

EXPORT_API Eina_Bool ewk_settings_compositing_borders_visible_set(Ewk_Settings *settings, Eina_Bool enable);

/**
 * Requests to set the scan malware enable/disable.
 *
 * @param settings settings object to set scan malware
 *
 * @param scan_malware_enabled @c EINA_TRUE to enable the scan malware
 *        @c EINA_FALSE to disable
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_scan_malware_enabled_set(Ewk_Settings *settings, Eina_Bool scan_malware_enabled);

/**
 * @brief Returns enable/disable scan malware.
 *
 * @since_tizen 2.3
 *
 * @param[in] settings settings object to get malware scan behavior
 *
 * @return @c EINA_TRUE on enable or @c EINA_FALSE on disable
 */
EXPORT_API Eina_Bool ewk_settings_scan_malware_enabled_get(const Ewk_Settings* settings);

/**
 * Requests to set using encoding detector.
 *
 * @deprecated Deprecated since Tizen 3.0.
 *
 * @param settings settings object to set using encoding detector
 * @param use use encoding detector
 *
 * @see ewk_settings_encoding_detector_enabled_set
 */
EINA_DEPRECATED EXPORT_API Eina_Bool ewk_settings_uses_encoding_detector_set(Ewk_Settings *settings, Eina_Bool use);

/**
 * @brief Requests to set the spdy enable/disable.
 *
 * @since_tizen 2.3
 *
 * @param[in] settings settings object to set spdy on soup
 * @param[in] spdy_enabled @c EINA_TRUE to enable the spdy on soup\n
 *        @c EINA_FALSE to disable
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_spdy_enabled_set(Ewk_Settings* settings, Eina_Bool spdy_enabled);

/**
 * @brief Returns enable/disable spdy on soup.
 *
 * @since_tizen 2.3
 *
 * @param[in] settings settings object to get spdy on soup
 *
 * @return @c EINA_TRUE on enable or @c EINA_FALSE on disable
 */
EXPORT_API Eina_Bool ewk_settings_spdy_enabled_get(const Ewk_Settings* settings);

/**
 * @brief Requests to set the performance features of soup enable/disable.
 *
 * @since_tizen 2.3
 *
 * @param[in] settings settings object to set performance features on soup
 * @param[in] performance_features_enabled @c EINA_TRUE to enable the performance features on soup\n
 *        @c EINA_FALSE to disable
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_performance_features_enabled_set(Ewk_Settings* settings, Eina_Bool performance_features_enabled);

/**
 * @brief Returns enable/disable performance features on soup.
 *
 * @since_tizen 2.3
 *
 * @param[in] settings settings object to get performance features
 *
 * @return @c EINA_TRUE on enable or @c EINA_FALSE on disable
 */
EXPORT_API Eina_Bool ewk_settings_performance_features_enabled_get(const Ewk_Settings* settings);

/**
 * Requests enables/disables to the specific extra feature
 *
 * @param settings setting object to enable/disable the specific extra feature
 * @param feature feature name
 * @param enable @c EINA_TRUE to enable the specific extra feature
 *        @c EINA_FALSE to disable
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API void ewk_settings_extra_feature_set(Ewk_Settings* settings, const char* feature, Eina_Bool enable);

/**
 * Returns enable/disable to the specific extra feature
 *
 * @param settings settings object to get whether the specific extra feature is enabled or not.
 * @param feature feature name
 *
 * @return @c EINA_TRUE on enable or @c EINA_FALSE on disable
 */
EXPORT_API Eina_Bool ewk_settings_extra_feature_get(const Ewk_Settings* settings, const char* feature);

/**
 * Enables/disables text autosizing.
 *
 * By default, the text autosizing is disabled.
 *
 * @param settings settings object to set the text autosizing
 * @param enable @c EINA_TRUE to enable the text autosizing
 *               @c EINA_FALSE to disable
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 *
 * @see ewk_settings_text_autosizing_enabled_get()
 */
EXPORT_API Eina_Bool ewk_settings_text_autosizing_enabled_set(Ewk_Settings *settings, Eina_Bool enable);

/**
 * Returns whether the text autosizing is enabled.
 *
 * The text autosizing is a feature which adjusts the font size of text in wide
 * columns, and makes text more legible.
 *
 * @param settings settings object to query whether text autosizing is enabled
 *
 * @return @c EINA_TRUE if the text autosizing is enabled
 *         @c EINA_FALSE if not or on failure
 */
EXPORT_API Eina_Bool ewk_settings_text_autosizing_enabled_get(const Ewk_Settings *settings);

/**
 * Sets the scale factor for text autosizing.
 *
 * Default value is 1.0.
 *
 * @param settings settings object to set the text autosizing
 * @param factor font scale factor for text autosizing
 */
EXPORT_API Eina_Bool ewk_settings_text_autosizing_font_scale_factor_set(Ewk_Settings *settings, double factor);

/**
 * Gets the current scale factor for text autosizing.
 *
 * @param settings settings object to set scale factor for text autosizing
 *
 * @return the current font scale factor for text autosizing.
 * In case of error, it returns non-positive value.
 */
EXPORT_API double ewk_settings_text_autosizing_font_scale_factor_get(const Ewk_Settings *settings);

/**
 * @brief Sets the scale factor for text autosizing.
 *
 * @details Default value is 1.0.
 *
 * @param settings settings object to set the text autosizing
 * @param factor font scale factor for text autosizing
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_text_autosizing_scale_factor_set(Ewk_Settings* settings, double factor);

/**
 * @brief Gets the current scale factor for text autosizing.
 *
 * @param settings settings object to set scale factor for text autosizing
 *
 * @return the current font scale factor for text autosizing
 */
EXPORT_API double ewk_settings_text_autosizing_scale_factor_get(const Ewk_Settings* settings);

/**
 * Sets text style for selection mode enabled.
 *
 * @param settings settings object
 * @param enabled text style for selection mode
 */

EXPORT_API void ewk_settings_text_style_state_enabled_set(Ewk_Settings *settings, Eina_Bool enabled);

/**
 * Gets text style for selection mode enabled.
 *
 * @param settings settings object
 *
 * @return @c EINA_TRUE if text style for selection mode enabled, @c EINA_FALSE otherwise
 */
EXPORT_API Eina_Bool ewk_settings_text_style_state_enabled_get(const Ewk_Settings *settings);

/**
 * @brief Enables/disables legacy font size mode
 *
 * @since_tizen @if MOBILE 2.3 @elseif WEARABLE 2.3.1 @endif
 *
 * @param[in] settings settings object
 * @param[in] enable If @c EINA_TRUE legacy font size is enabled\n
 *            otherwise @c EINA_FALSE to disable it
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_legacy_font_size_enabled_set(Ewk_Settings* settings, Eina_Bool enabled);

/**
 * @brief Return whether legacy font size mode is enabled
 *
 * @since_tizen @if MOBILE 2.3 @elseif WEARABLE 2.3.1 @endif
 *
 * @param[in] settings settings object
 *
 * @return @c EINA_TRUE if Ewk_Legacy_Font_Size_Mode set to legacy font size mode
 *        @c EINA_FALSE if Ewk_Legacy_Font_Size_Mode not set to legacy font size mode
 */
EXPORT_API Eina_Bool ewk_settings_legacy_font_size_enabled_get(Ewk_Settings* settings);

/**
 * @brief Sets font-family as system font for font rendering
 *
 * @since_tizen @if MOBILE 2.3 @elseif WEARABLE 2.3.1 @endif
 *
 * @param[in] settings settings object
 * @param[in] use @c EINA_TRUE to use one of the system fonts which is selected by user in Settings
 *               @c EINA_FALSE to use a system default font
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_use_system_font_set(Ewk_Settings* settings, Eina_Bool use);

/**
 * @brief Returns whether we use the system font which is selected by user in Settings or use a system default font
 *
 * @since_tizen @if MOBILE 2.3 @elseif WEARABLE 2.3.1 @endif
 *
 * @param[in] settings settings object
 *
 * @return @c EINA_TRUE if we use the sysem font which is selected by user in Settings
 *        @c EINA_FALSE if we use a system default font or on failure
 */
EXPORT_API Eina_Bool ewk_settings_use_system_font_get(Ewk_Settings* settings);

/**
 * Requests enables/disables the plug-ins.
 *
 * @param settings settings object to set the plug-ins
 * @param enable @c EINA_TRUE to enable the plug-ins
 *        @c EINA_FALSE to disable
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_plugins_enabled_set(Ewk_Settings *settings, Eina_Bool enable);

/**
 * Requests to set using keypad without user action (default value : true)
 *
 * @param settings settings object using keypad without user action
 * @param enable @c EINA_TRUE to use without user action @c EINA_FALSE to disable
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_uses_keypad_without_user_action_set(Ewk_Settings *settings, Eina_Bool enable);

/**
 * Requests enable/disable text selection by default WebKit.
 *
 * @param settings setting object to set text selection by default WebKit
 * @param enable @c EINA_TRUE to enable text selection by default WebKit
 *        @c EINA_FALSE to disable
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_text_selection_enabled_set(Ewk_Settings* settings, Eina_Bool enable);

/**
 * Requests to enable/disable to select word by double tap
 *
 * @param settings settings object to enable/disable to select word by double tap
 * @param enable @c EINA_TRUE to select word by double tap
 *        @c EINA_FALSE to disable
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_select_word_automatically_set(Ewk_Settings *settings, Eina_Bool enabled);

/**
 * Sets to paste image as URI (default: paste as base64-encoded-data)
 *
 * @param settings settings object
* @param enable @c EINA_TRUE to paste image as URI    @c EINA_FALSE to paste image as data
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_paste_image_uri_mode_set(Ewk_Settings *settings, Eina_Bool enabled);


/**
 * Gets the initial position value for the HTML list element <ul></ul>.
 *
 * @param settings setting object to get the initial position value
 *
 * @return the initial position value for the HTML list element.
 */
EXPORT_API Ewk_List_Style_Position ewk_settings_initial_list_style_position_get(const Ewk_Settings* settings);

/**
 * Sets the initial position value for the HTML list element <ul></ul>.
 *
 * This value affect the lists that are going to be created,
 * does not make sense to manipulate it for existed elements.
 *
 * @param settings setting object to set the initial list style position
 * @param style a new style to set
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_initial_list_style_position_set(Ewk_Settings* settings, Ewk_List_Style_Position style);

/**
 * Enable or disable supporting of -webkit-text-size-adjust
 *
 * -webkit-text-size-adjust affects text size adjusting feature.
 *
 * @param settings setting object to set the support of -webkit-text-size-adjust
 * @param enable @c EINA_TRUE to support -webkit-text-size-adjust, @c EINA_FALSE not to support
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_settings_webkit_text_size_adjust_enabled_set(Ewk_Settings* settings, Eina_Bool enabled);

/**
 * @brief Gets the staus of -webkit-text-size-adjust supporting.
 *
 * @since_tizen 2.3
 *
 * @param[in] settings setting object to get the status of -webkit-text-size-adjust supporting
 *
 * @return the status of -webkit-text-size-adjust supporting
 */
EXPORT_API Eina_Bool ewk_settings_webkit_text_size_adjust_enabled_get(const Ewk_Settings* settings);

/**
 * @brief Requests enables/disables to control text selection handles from app
 *
 * @since_tizen 2.3
 *
 * @param[in] settings setting object to set to control text selection handles from app
 * @param[in] enable @c EINA_TRUE to control text selection handles from app\n
 *        @c EINA_FALSE to disable
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API void ewk_settings_selection_handle_enabled_set(Ewk_Settings* settings, Eina_Bool enable);

/**
 * @brief Returns whether text selection handles are controlled from app or not
 *
 * @since_tizen 2.3
 *
 * @param[in] settings setting object to get whether text selection handles are controlled from app or not
 *
 * @return @c EINA_TRUE if text selection handles are controlled from app\n
 *         @c EINA_FALSE if not or on failure
 */
EXPORT_API Eina_Bool ewk_settings_selection_handle_enabled_get(const Ewk_Settings* settings);

#ifdef __cplusplus
}
#endif
#endif // ewk_settings_product_h
