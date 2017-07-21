/*
 * Copyright © 2012, 2013 Intel Corporation
 *
 * Permission to use, copy, modify, distribute, and sell this
 * software and its documentation for any purpose is hereby granted
 * without fee, provided that the above copyright notice appear in
 * all copies and that both that copyright notice and this permission
 * notice appear in supporting documentation, and that the name of
 * the copyright holders not be used in advertising or publicity
 * pertaining to distribution of the software without specific,
 * written prior permission.  The copyright holders make no
 * representations about the suitability of this software for any
 * purpose.  It is provided "as is" without express or implied
 * warranty.
 *
 * THE COPYRIGHT HOLDERS DISCLAIM ALL WARRANTIES WITH REGARD TO THIS
 * SOFTWARE, INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS, IN NO EVENT SHALL THE COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN
 * AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION,
 * ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF
 * THIS SOFTWARE.
 */

#ifndef INPUT_METHOD_CLIENT_PROTOCOL_H
#define INPUT_METHOD_CLIENT_PROTOCOL_H

#ifdef  __cplusplus
extern "C" {
#endif

#include <stdint.h>
#include <stddef.h>
#include "wayland-client.h"

struct wl_client;
struct wl_resource;

struct wl_input_method;
struct wl_input_method_context;
struct wl_input_panel;
struct wl_input_panel_surface;
struct wl_keyboard;
struct wl_output;
struct wl_surface;

extern const struct wl_interface wl_input_method_context_interface;
extern const struct wl_interface wl_input_method_interface;
extern const struct wl_interface wl_input_panel_interface;
extern const struct wl_interface wl_input_panel_surface_interface;

/**
 * wl_input_method_context - input method context
 * @reset: (none)
 * @content_type: (none)
 * @invoke_action: (none)
 * @commit_state: (none)
 * @preferred_language: (none)
 * @return_key_type: (none)
 * @return_key_disabled: (none)
 * @input_panel_data: (none)
 * @bidi_direction: (none)
 * @cursor_position: (none)
 * @process_input_device_event: request to process unconventional input
 *	device event
 * @filter_key_event: input panel data
 * @reset_sync: (none)
 *
 * Corresponds to a text model on input method side. An input method
 * context is created on text model activation on the input method side. It
 * allows to receive information about the text model from the application
 * via events. Input method contexts do not keep state after deactivation
 * and should be destroyed after deactivation is handled.
 *
 * Text is generally UTF-8 encoded, indices and lengths are in bytes.
 *
 * Serials are used to synchronize the state between the text input and an
 * input method. New serials are sent by the text input in the commit_state
 * request and are used by the input method to indicate the known text
 * input state in events like preedit_string, commit_string, and keysym.
 * The text input can then ignore events from the input method which are
 * based on an outdated state (for example after a reset).
 */
struct wl_input_method_context_listener {
	/**
	 * reset - (none)
	 */
	void (*reset)(void *data,
		      struct wl_input_method_context *wl_input_method_context);
	/**
	 * content_type - (none)
	 * @hint: (none)
	 * @purpose: (none)
	 */
	void (*content_type)(void *data,
			     struct wl_input_method_context *wl_input_method_context,
			     uint32_t hint,
			     uint32_t purpose);
	/**
	 * invoke_action - (none)
	 * @button: (none)
	 * @index: (none)
	 */
	void (*invoke_action)(void *data,
			      struct wl_input_method_context *wl_input_method_context,
			      uint32_t button,
			      uint32_t index);
	/**
	 * commit_state - (none)
	 * @serial: serial of text input state
	 */
	void (*commit_state)(void *data,
			     struct wl_input_method_context *wl_input_method_context,
			     uint32_t serial);
	/**
	 * preferred_language - (none)
	 * @language: (none)
	 */
	void (*preferred_language)(void *data,
				   struct wl_input_method_context *wl_input_method_context,
				   const char *language);
	/**
	 * return_key_type - (none)
	 * @return_key_type: (none)
	 */
	void (*return_key_type)(void *data,
				struct wl_input_method_context *wl_input_method_context,
				uint32_t return_key_type);
	/**
	 * return_key_disabled - (none)
	 * @return_key_disabled: (none)
	 */
	void (*return_key_disabled)(void *data,
				    struct wl_input_method_context *wl_input_method_context,
				    uint32_t return_key_disabled);
	/**
	 * input_panel_data - (none)
	 * @input_panel_data: (none)
	 * @input_panel_data_length: (none)
	 */
	void (*input_panel_data)(void *data,
				 struct wl_input_method_context *wl_input_method_context,
				 const char *input_panel_data,
				 uint32_t input_panel_data_length);
	/**
	 * bidi_direction - (none)
	 * @direction: (none)
	 */
	void (*bidi_direction)(void *data,
			       struct wl_input_method_context *wl_input_method_context,
			       uint32_t direction);
	/**
	 * cursor_position - (none)
	 * @cursor_position: (none)
	 */
	void (*cursor_position)(void *data,
				struct wl_input_method_context *wl_input_method_context,
				uint32_t cursor_position);
	/**
	 * process_input_device_event - request to process unconventional
	 *	input device event
	 * @event_type: (none)
	 * @event_data: (none)
	 * @event_length: (none)
	 *
	 * Deliver unconventional input device events that need to be
	 * processed by input panel
	 */
	void (*process_input_device_event)(void *data,
					   struct wl_input_method_context *wl_input_method_context,
					   uint32_t event_type,
					   const char *event_data,
					   uint32_t event_length);
	/**
	 * filter_key_event - input panel data
	 * @serial: (none)
	 * @time: (none)
	 * @keyname: (none)
	 * @state: (none)
	 * @modifiers: (none)
	 *
	 * Request to filter key event via input method
	 */
	void (*filter_key_event)(void *data,
				 struct wl_input_method_context *wl_input_method_context,
				 uint32_t serial,
				 uint32_t time,
				 const char *keyname,
				 uint32_t state,
				 uint32_t modifiers);
	/**
	 * reset_sync - (none)
	 * @serial: (none)
	 */
	void (*reset_sync)(void *data,
			   struct wl_input_method_context *wl_input_method_context,
			   uint32_t serial);
};

static inline int
wl_input_method_context_add_listener(struct wl_input_method_context *wl_input_method_context,
				     const struct wl_input_method_context_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) wl_input_method_context,
				     (void (**)(void)) listener, data);
}

#define WL_INPUT_METHOD_CONTEXT_DESTROY	0
#define WL_INPUT_METHOD_CONTEXT_COMMIT_STRING	1
#define WL_INPUT_METHOD_CONTEXT_PREEDIT_STRING	2
#define WL_INPUT_METHOD_CONTEXT_PREEDIT_STYLING	3
#define WL_INPUT_METHOD_CONTEXT_PREEDIT_CURSOR	4
#define WL_INPUT_METHOD_CONTEXT_DELETE_SURROUNDING_TEXT	5
#define WL_INPUT_METHOD_CONTEXT_CURSOR_POSITION	6
#define WL_INPUT_METHOD_CONTEXT_MODIFIERS_MAP	7
#define WL_INPUT_METHOD_CONTEXT_KEYSYM	8
#define WL_INPUT_METHOD_CONTEXT_GRAB_KEYBOARD	9
#define WL_INPUT_METHOD_CONTEXT_KEY	10
#define WL_INPUT_METHOD_CONTEXT_MODIFIERS	11
#define WL_INPUT_METHOD_CONTEXT_LANGUAGE	12
#define WL_INPUT_METHOD_CONTEXT_TEXT_DIRECTION	13
#define WL_INPUT_METHOD_CONTEXT_SELECTION_REGION	14
#define WL_INPUT_METHOD_CONTEXT_PRIVATE_COMMAND	15
#define WL_INPUT_METHOD_CONTEXT_UPDATE_INPUT_PANEL_DATA	16
#define WL_INPUT_METHOD_CONTEXT_HIDE_INPUT_PANEL	17
#define WL_INPUT_METHOD_CONTEXT_GET_SELECTION_TEXT	18
#define WL_INPUT_METHOD_CONTEXT_GET_SURROUNDING_TEXT	19
#define WL_INPUT_METHOD_CONTEXT_FILTER_KEY_EVENT_DONE	20
#define WL_INPUT_METHOD_CONTEXT_RESET_DONE	21

#define WL_INPUT_METHOD_CONTEXT_DESTROY_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_COMMIT_STRING_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_PREEDIT_STRING_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_PREEDIT_STYLING_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_PREEDIT_CURSOR_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_DELETE_SURROUNDING_TEXT_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_CURSOR_POSITION_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_MODIFIERS_MAP_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_KEYSYM_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_GRAB_KEYBOARD_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_KEY_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_MODIFIERS_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_LANGUAGE_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_TEXT_DIRECTION_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_SELECTION_REGION_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_PRIVATE_COMMAND_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_UPDATE_INPUT_PANEL_DATA_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_HIDE_INPUT_PANEL_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_GET_SELECTION_TEXT_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_GET_SURROUNDING_TEXT_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_FILTER_KEY_EVENT_DONE_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_RESET_DONE_SINCE_VERSION	1

static inline void
wl_input_method_context_set_user_data(struct wl_input_method_context *wl_input_method_context, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) wl_input_method_context, user_data);
}

static inline void *
wl_input_method_context_get_user_data(struct wl_input_method_context *wl_input_method_context)
{
	return wl_proxy_get_user_data((struct wl_proxy *) wl_input_method_context);
}

static inline uint32_t
wl_input_method_context_get_version(struct wl_input_method_context *wl_input_method_context)
{
	return wl_proxy_get_version((struct wl_proxy *) wl_input_method_context);
}

static inline void
wl_input_method_context_destroy(struct wl_input_method_context *wl_input_method_context)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) wl_input_method_context);
}

static inline void
wl_input_method_context_commit_string(struct wl_input_method_context *wl_input_method_context, uint32_t serial, const char *text)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_COMMIT_STRING, serial, text);
}

static inline void
wl_input_method_context_preedit_string(struct wl_input_method_context *wl_input_method_context, uint32_t serial, const char *text, const char *commit)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_PREEDIT_STRING, serial, text, commit);
}

static inline void
wl_input_method_context_preedit_styling(struct wl_input_method_context *wl_input_method_context, uint32_t index, uint32_t length, uint32_t style)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_PREEDIT_STYLING, index, length, style);
}

static inline void
wl_input_method_context_preedit_cursor(struct wl_input_method_context *wl_input_method_context, int32_t index)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_PREEDIT_CURSOR, index);
}

static inline void
wl_input_method_context_delete_surrounding_text(struct wl_input_method_context *wl_input_method_context, int32_t index, uint32_t length)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_DELETE_SURROUNDING_TEXT, index, length);
}

static inline void
wl_input_method_context_cursor_position(struct wl_input_method_context *wl_input_method_context, int32_t index, int32_t anchor)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_CURSOR_POSITION, index, anchor);
}

static inline void
wl_input_method_context_modifiers_map(struct wl_input_method_context *wl_input_method_context, struct wl_array *map)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_MODIFIERS_MAP, map);
}

static inline void
wl_input_method_context_keysym(struct wl_input_method_context *wl_input_method_context, uint32_t serial, uint32_t time, uint32_t sym, uint32_t state, uint32_t modifiers)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_KEYSYM, serial, time, sym, state, modifiers);
}

static inline struct wl_keyboard *
wl_input_method_context_grab_keyboard(struct wl_input_method_context *wl_input_method_context)
{
	struct wl_proxy *keyboard;

	keyboard = wl_proxy_marshal_constructor((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_GRAB_KEYBOARD, &wl_keyboard_interface, NULL);

	return (struct wl_keyboard *) keyboard;
}

static inline void
wl_input_method_context_key(struct wl_input_method_context *wl_input_method_context, uint32_t serial, uint32_t time, uint32_t key, uint32_t state)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_KEY, serial, time, key, state);
}

static inline void
wl_input_method_context_modifiers(struct wl_input_method_context *wl_input_method_context, uint32_t serial, uint32_t mods_depressed, uint32_t mods_latched, uint32_t mods_locked, uint32_t group)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_MODIFIERS, serial, mods_depressed, mods_latched, mods_locked, group);
}

static inline void
wl_input_method_context_language(struct wl_input_method_context *wl_input_method_context, uint32_t serial, const char *language)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_LANGUAGE, serial, language);
}

static inline void
wl_input_method_context_text_direction(struct wl_input_method_context *wl_input_method_context, uint32_t serial, uint32_t direction)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_TEXT_DIRECTION, serial, direction);
}

static inline void
wl_input_method_context_selection_region(struct wl_input_method_context *wl_input_method_context, uint32_t serial, int32_t start, int32_t end)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_SELECTION_REGION, serial, start, end);
}

static inline void
wl_input_method_context_private_command(struct wl_input_method_context *wl_input_method_context, uint32_t serial, const char *command)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_PRIVATE_COMMAND, serial, command);
}

static inline void
wl_input_method_context_update_input_panel_data(struct wl_input_method_context *wl_input_method_context, uint32_t serial, const char *input_panel_data, uint32_t input_panel_data_length)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_UPDATE_INPUT_PANEL_DATA, serial, input_panel_data, input_panel_data_length);
}

static inline void
wl_input_method_context_hide_input_panel(struct wl_input_method_context *wl_input_method_context, uint32_t serial)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_HIDE_INPUT_PANEL, serial);
}

static inline void
wl_input_method_context_get_selection_text(struct wl_input_method_context *wl_input_method_context, int32_t fd)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_GET_SELECTION_TEXT, fd);
}

static inline void
wl_input_method_context_get_surrounding_text(struct wl_input_method_context *wl_input_method_context, uint32_t maxlen_before, uint32_t maxlen_after, int32_t fd)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_GET_SURROUNDING_TEXT, maxlen_before, maxlen_after, fd);
}

static inline void
wl_input_method_context_filter_key_event_done(struct wl_input_method_context *wl_input_method_context, uint32_t serial, uint32_t state)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_FILTER_KEY_EVENT_DONE, serial, state);
}

static inline void
wl_input_method_context_reset_done(struct wl_input_method_context *wl_input_method_context, uint32_t serial)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_method_context,
			 WL_INPUT_METHOD_CONTEXT_RESET_DONE, serial);
}

/**
 * wl_input_method - input method
 * @activate: activate event
 * @deactivate: deactivate event
 * @show_input_panel: show input panel event
 * @hide_input_panel: hide input panel event
 *
 * An input method object is responsible to compose text in response to
 * input from hardware or virtual keyboards. There is one input method
 * object per seat. On activate there is a new input method context object
 * created which allows the input method to communicate with the text
 * model.
 */
struct wl_input_method_listener {
	/**
	 * activate - activate event
	 * @id: (none)
	 * @text_input_id: (none)
	 *
	 * A text model was activated. Creates an input method context
	 * object which allows communication with the text model.
	 */
	void (*activate)(void *data,
			 struct wl_input_method *wl_input_method,
			 struct wl_input_method_context *id,
			 uint32_t text_input_id);
	/**
	 * deactivate - deactivate event
	 * @context: (none)
	 *
	 * The text model corresponding to the context argument was
	 * deactivated. The input method context should be destroyed after
	 * deactivation is handled.
	 */
	void (*deactivate)(void *data,
			   struct wl_input_method *wl_input_method,
			   struct wl_input_method_context *context);
	/**
	 * show_input_panel - show input panel event
	 * @context: (none)
	 *
	 * Input panel (virtual keyboard) was requested to show.
	 */
	void (*show_input_panel)(void *data,
				 struct wl_input_method *wl_input_method,
				 struct wl_input_method_context *context);
	/**
	 * hide_input_panel - hide input panel event
	 * @context: (none)
	 *
	 * Input panel (virtual keyboard) was requested to hide.
	 */
	void (*hide_input_panel)(void *data,
				 struct wl_input_method *wl_input_method,
				 struct wl_input_method_context *context);
};

static inline int
wl_input_method_add_listener(struct wl_input_method *wl_input_method,
			     const struct wl_input_method_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) wl_input_method,
				     (void (**)(void)) listener, data);
}


static inline void
wl_input_method_set_user_data(struct wl_input_method *wl_input_method, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) wl_input_method, user_data);
}

static inline void *
wl_input_method_get_user_data(struct wl_input_method *wl_input_method)
{
	return wl_proxy_get_user_data((struct wl_proxy *) wl_input_method);
}

static inline uint32_t
wl_input_method_get_version(struct wl_input_method *wl_input_method)
{
	return wl_proxy_get_version((struct wl_proxy *) wl_input_method);
}

static inline void
wl_input_method_destroy(struct wl_input_method *wl_input_method)
{
	wl_proxy_destroy((struct wl_proxy *) wl_input_method);
}

#define WL_INPUT_PANEL_GET_INPUT_PANEL_SURFACE	0

#define WL_INPUT_PANEL_GET_INPUT_PANEL_SURFACE_SINCE_VERSION	1

static inline void
wl_input_panel_set_user_data(struct wl_input_panel *wl_input_panel, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) wl_input_panel, user_data);
}

static inline void *
wl_input_panel_get_user_data(struct wl_input_panel *wl_input_panel)
{
	return wl_proxy_get_user_data((struct wl_proxy *) wl_input_panel);
}

static inline uint32_t
wl_input_panel_get_version(struct wl_input_panel *wl_input_panel)
{
	return wl_proxy_get_version((struct wl_proxy *) wl_input_panel);
}

static inline void
wl_input_panel_destroy(struct wl_input_panel *wl_input_panel)
{
	wl_proxy_destroy((struct wl_proxy *) wl_input_panel);
}

static inline struct wl_input_panel_surface *
wl_input_panel_get_input_panel_surface(struct wl_input_panel *wl_input_panel, struct wl_surface *surface)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) wl_input_panel,
			 WL_INPUT_PANEL_GET_INPUT_PANEL_SURFACE, &wl_input_panel_surface_interface, NULL, surface);

	return (struct wl_input_panel_surface *) id;
}

#ifndef WL_INPUT_PANEL_SURFACE_POSITION_ENUM
#define WL_INPUT_PANEL_SURFACE_POSITION_ENUM
enum wl_input_panel_surface_position {
	WL_INPUT_PANEL_SURFACE_POSITION_CENTER_BOTTOM = 0,
};
#endif /* WL_INPUT_PANEL_SURFACE_POSITION_ENUM */

#define WL_INPUT_PANEL_SURFACE_SET_TOPLEVEL	0
#define WL_INPUT_PANEL_SURFACE_SET_OVERLAY_PANEL	1
#define WL_INPUT_PANEL_SURFACE_SET_READY	2

#define WL_INPUT_PANEL_SURFACE_SET_TOPLEVEL_SINCE_VERSION	1
#define WL_INPUT_PANEL_SURFACE_SET_OVERLAY_PANEL_SINCE_VERSION	1
#define WL_INPUT_PANEL_SURFACE_SET_READY_SINCE_VERSION	1

static inline void
wl_input_panel_surface_set_user_data(struct wl_input_panel_surface *wl_input_panel_surface, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) wl_input_panel_surface, user_data);
}

static inline void *
wl_input_panel_surface_get_user_data(struct wl_input_panel_surface *wl_input_panel_surface)
{
	return wl_proxy_get_user_data((struct wl_proxy *) wl_input_panel_surface);
}

static inline uint32_t
wl_input_panel_surface_get_version(struct wl_input_panel_surface *wl_input_panel_surface)
{
	return wl_proxy_get_version((struct wl_proxy *) wl_input_panel_surface);
}

static inline void
wl_input_panel_surface_destroy(struct wl_input_panel_surface *wl_input_panel_surface)
{
	wl_proxy_destroy((struct wl_proxy *) wl_input_panel_surface);
}

static inline void
wl_input_panel_surface_set_toplevel(struct wl_input_panel_surface *wl_input_panel_surface, struct wl_output *output, uint32_t position)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_panel_surface,
			 WL_INPUT_PANEL_SURFACE_SET_TOPLEVEL, output, position);
}

static inline void
wl_input_panel_surface_set_overlay_panel(struct wl_input_panel_surface *wl_input_panel_surface)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_panel_surface,
			 WL_INPUT_PANEL_SURFACE_SET_OVERLAY_PANEL);
}

static inline void
wl_input_panel_surface_set_ready(struct wl_input_panel_surface *wl_input_panel_surface, uint32_t state)
{
	wl_proxy_marshal((struct wl_proxy *) wl_input_panel_surface,
			 WL_INPUT_PANEL_SURFACE_SET_READY, state);
}

#ifdef  __cplusplus
}
#endif

#endif
