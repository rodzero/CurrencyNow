package com.currencynowsneer.currencynow;

/**
 * Created by Rodolfo on 27/09/2015.
 */
public class Cotacao {

    private Float cotacao;
    private Float variacao;

    public Cotacao(Float cotacao, Float variacao) {
        this.cotacao = cotacao;
        this.variacao = variacao;
    }

    public Float getCotacao() {
        return cotacao;
    }

    public void setCotacao(Float cotacao) {
        this.cotacao = cotacao;
    }

    public Float getVariacao() {
        return variacao;
    }

    public void setVariacao(Float variacao) {
        this.variacao = variacao;
    }
}
