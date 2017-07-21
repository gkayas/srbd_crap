/*
 * Copyright (C) 2013-2016 Samsung Electronics. All rights reserved.
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
 * THIS SOFTWARE IS PROVIDED BY SAMSUNG ELECTRONICS. AND ITS CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL SAMSUNG ELECTRONICS. OR ITS
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

#ifndef ewk_user_media_internal_h
#define ewk_user_media_internal_h

#include <Eina.h>
#include <tizen.h>
#include "ewk_security_origin_internal.h"

#ifdef __cplusplus
extern "C" {
#endif

typedef struct _Ewk_User_Media_Permission_Request Ewk_User_Media_Permission_Request;

/**
 * Requests for getting origin of local media permission request.
 *
 * @param request Ewk_User_Media_Permission_Request object to get origin for
 *        userMedia permission request
 *
 * @return security origin of userMedia permission request
 */
EXPORT_API const Ewk_Security_Origin* ewk_user_media_permission_request_origin_get(const Ewk_User_Media_Permission_Request* request);

/**
 * Sets the permission to access local media
 *
 * @param request Ewk_View_User_Media_Permission_Request object for userMedia
 *        permission
 * @param allowed decided permission value from user
 */
EXPORT_API void ewk_user_media_permission_request_set(Ewk_User_Media_Permission_Request* request, Eina_Bool allowed);

/**
 * Suspend the operation for user media permission
 *
 * @param request user media permission request object
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_user_media_permission_request_suspend(Ewk_User_Media_Permission_Request* request);

/**
 * Reply the result about user media permission.
 *
 * @param request Ewk_User_Media_Permission_Request object to get
 *        the information about user media permission request
 * @param allow result about user media permission
 */
EXPORT_API void ewk_user_media_permission_reply(Ewk_User_Media_Permission_Request* request, Eina_Bool allow);

#ifdef __cplusplus
}
#endif
#endif // ewk_user_media_internal_h
