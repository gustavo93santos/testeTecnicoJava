package com.mycompany.relatoriofuncionarios;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author gusta
 */
public class Funcionario extends Pessoa{
    protected BigDecimal salario;
    protected String funcao;
    
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao){
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }
    
    public long getSalario(){
        return this.salario.longValueExact();
    }
   
}