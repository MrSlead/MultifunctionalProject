package com.almod.api.factory;

import java.util.Optional;

public interface EntityFactory<S, D> {
    Optional<D> getEntity(S dto);
}
