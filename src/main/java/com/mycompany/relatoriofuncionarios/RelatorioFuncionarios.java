package com.mycompany.relatoriofuncionarios;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author GUSTAVO
 */
public class RelatorioFuncionarios {

    public static void main(String[] args) {
        //3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        List<Funcionario> funcionarios = cargaFuncionarios();
        //3.2 – Remover o funcionário “João” da lista.
        deletaFuncionario(funcionarios, "João");
        //3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
        listaFuncionarios(funcionarios);
        //3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        funcionarios = aplicarAumento(funcionarios ,new BigDecimal(1.1));
        //3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(funcionario -> funcionario.funcao));
        //3.6 – Imprimir os funcionários, agrupados por função.
        listaFuncionariosFuncao(funcionariosPorFuncao);
        //3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        listaFuncionarios(funcionarios.stream().filter(funcionario -> funcionario.dataNascimento.getMonthValue() == 10 || funcionario.dataNascimento.getMonthValue() == 12).collect(Collectors.toList()));
        //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        funcMaisVelho(funcionarios);
        //3.10 – Imprimir a lista de funcionários por ordem alfabética.
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        listaFuncionarios(funcionarios);
        //3.11 – Imprimir o total dos salários dos funcionários.
        somaSalarios(funcionarios);
        //3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        listaFuncionariosSalariosMinimo(funcionarios,new BigDecimal("1212.00"));
    }
    
    
    public static List<Funcionario> cargaFuncionarios(){
        List<Funcionario> temp = new ArrayList<>();
        temp.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        temp.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        temp.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));  
        temp.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        temp.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        temp.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        temp.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        temp.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        temp.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        temp.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));        
        return temp;
    }
   
    public static List<Funcionario> deletaFuncionario(List<Funcionario> funcionarios, String nomeDeletar){
        funcionarios.removeIf(funcionario -> funcionario.nome.equals(nomeDeletar));
        return funcionarios;
    }
    
    public static void listaFuncionarios(List<Funcionario> funcionarios){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        funcionarios.forEach(funcionario -> System.out.printf("Nome: %s, Data Nascimento: %s, Salário: %,.2f, Função: %s\n",
                funcionario.nome, funcionario.dataNascimento.format(dateFormatter),
                funcionario.salario, funcionario.funcao));
    }
    
    public static List<Funcionario> aplicarAumento(List<Funcionario> funcionarios, BigDecimal porcentagemAumento){     
        if(porcentagemAumento.doubleValue() > 1){
            funcionarios.forEach(funcionario -> funcionario.salario = funcionario.salario.multiply(porcentagemAumento));
        }else{
            System.out.println("Não é possivel realizar a redução de salarios.");
        }
        return funcionarios;
    };
 
    public static void listaFuncionariosFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao){
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(funcionario -> System.out.println("  " + funcionario.nome));
        });
    };
    
    public static void funcMaisVelho(List<Funcionario> funcionarios){
        Funcionario temp = funcionarios.get(0);
        for (Funcionario func : funcionarios) {    
            if(func.getIdadeDias() > temp.getIdadeDias() ){
                temp = func;               
            }
        }
        System.out.printf("%s tem %s anos de idade e é nosso funcionario mais velho\n",
                temp.nome, ChronoUnit.YEARS.between(temp.dataNascimento, LocalDate.now()));
    }
    
    public static void listaFuncionariosSalariosMinimo(List<Funcionario> funcionarios, BigDecimal salarioMinimo){
        funcionarios.forEach(funcionario -> System.out.printf("%s recebe %,.0f Salarios minimos\n",
                funcionario.nome, 
                funcionario.salario.divide(salarioMinimo, RoundingMode.DOWN)));
    };
    
    public static void somaSalarios(List<Funcionario> funcionarios){
        BigDecimal soma = new BigDecimal("0.0"); 
        for (Funcionario func : funcionarios) {    
            soma = soma.add(func.salario);
        }
        System.out.printf("A soma do salario de todos os funcionarios é %,.2f \n", soma);
    }; 
    
}