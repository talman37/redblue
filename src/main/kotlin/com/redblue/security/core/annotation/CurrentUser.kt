package com.redblue.security.core.annotation

import org.springframework.security.core.annotation.AuthenticationPrincipal

@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : lawFirmUser")
annotation class CurrentUser