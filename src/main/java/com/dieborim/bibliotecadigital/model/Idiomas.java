package com.dieborim.bibliotecadigital.model;

import java.util.List;

public enum Idiomas {

    /**
     * Un enum es un tipo especial de clase que se usa para definir un
     * conjunto fijo de constantes.
     */

    en ("inglés"),
    fr ("francés"),
    de ("alemán"),
    es ("español"),
    it ("italiano"),
    fi ("finés"),
    sv ("sueco"),
    pt ("portugués"),
    la ("latín");

    private String idiomasGutendex;

    Idiomas(String idiomasGutendex){
        this.idiomasGutendex = idiomasGutendex;
    }

    public static Idiomas fromList(List<String> idiomas){
        //Compara ese primer idioma del libro con todos los valores que tenemos en tu enum Idiomas
        for (Idiomas idioma : Idiomas.values()){
            //Si lo encuentra lo devuelve como resultado
            if(idioma.idiomasGutendex.equalsIgnoreCase(idiomas.get(0))){
                return idioma;
            }
        }
        //Si no lo encuentra, lanza una excepción para decir que ese idioma no es válido
        throw new IllegalArgumentException("Ningún idioma encontrado: "+idiomas.get(0));
    }


}
