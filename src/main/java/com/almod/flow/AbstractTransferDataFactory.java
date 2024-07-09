package com.almod.flow;

public interface AbstractTransferDataFactory<T> {
    AbstractTransferData<T> getDependenceForTransferData(T entity);
}
