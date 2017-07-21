/*
 * vconf-internal-keys
 *
 * Copyright (c) 2000 - 2011 Samsung Electronics Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

#ifndef __VCONF_INTERNAL_IDLE_LOCK_KEYS_H__
#define __VCONF_INTERNAL_IDLE_LOCK_KEYS_H__
#define VCONFKEY_IDLE_LOCK_BGSET  "db/idle_lock/bgset"

#define VCONFKEY_IDLE_LOCK_STATE  "memory/idle_lock/state"

enum {
	VCONFKEY_IDLE_UNLOCK = 0,
		VCONFKEY_IDLE_LOCK,
		VCONFKEY_IDLE_LAUNCHING_LOCK
};

#define VCONFKEY_IDLE_LOCK_STATE_READ_ONLY  "memory/idle_lock/state_read_only"

#endif /*   __VCONF_INTERNAL_IDLE_LOCK_KEYS_H__    */
