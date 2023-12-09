package com.mycompany.relatoriofuncionarios;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author gusta
 */
public class Pessoa {
    protected String nome;
    protected LocalDate dataNascimento;
    
    Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    };
    
    public long getIdadeDias(){
        return ChronoUnit.DAYS.between(this.dataNascimento, LocalDate.now());   
    };
    
    public String getNome(){
        return this.nome;   
    };
}
