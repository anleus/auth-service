package com.anleus.authservice.models.ts;

import com.anleus.authservice.models.AppUser;

public record LoginResponseTs(AppUser user, String jwt) {}
