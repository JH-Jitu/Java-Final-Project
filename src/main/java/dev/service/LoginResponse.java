package dev.service;

import dev.domain.UsersEntity;

public record LoginResponse(UsersEntity user, Object roleEntity) {
}
