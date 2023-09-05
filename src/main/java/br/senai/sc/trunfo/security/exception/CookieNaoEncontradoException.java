package br.senai.sc.trunfo.security.exception;

public class CookieNaoEncontradoException extends Exception {
    public CookieNaoEncontradoException(String cookie) {
        super("Cookie " + cookie + "não encontrado!");
    }
}
