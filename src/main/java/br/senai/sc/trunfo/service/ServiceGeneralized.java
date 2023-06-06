package br.senai.sc.trunfo.service;

import java.util.List;

public interface ServiceGeneralized<T, R, S> {
    T save(R object);
    T list(S id);
    void delete(S id);
}
