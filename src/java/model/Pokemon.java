/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Vinicius
 */
public class Pokemon {
    private String nome;
    private String cp;
    private String gen;
    private String candy;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }
    
    public String getCandy() {
        return candy;
    }

    public void setCandy(String candy) {
        this.candy = candy;
    }
}
