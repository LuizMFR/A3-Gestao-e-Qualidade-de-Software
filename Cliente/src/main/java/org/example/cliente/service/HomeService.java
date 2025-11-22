package org.example.cliente.service;


import java.util.Collections;
import java.util.List;

import org.example.cliente.entities.Categoria;
import org.example.cliente.entities.Transacao;
import org.example.cliente.repository.HomeRepository;




public class HomeService {

    private HomeRepository homeRepository = new HomeRepository();
 
    public HomeService() {

    }

    public List<Transacao> getAllTransacoes(Integer userId) {
        List<Transacao> lista = homeRepository.findByUserId(userId);
        return lista != null ? lista : List.of();
    }

    public List<Categoria> getAllCategorias(int userId) {
        List<Categoria> categorias = homeRepository.findCategoriasByUserId(userId);
        return categorias != null ? categorias : Collections.emptyList();
    }

    public int getCategoriaIdByDesc(List<Categoria> categorias, String descricao) {
         
        
        if (categorias != null) {
            for (Categoria categoria : categorias) {
                System.out.println("DEBUG Comparando: " + categoria.getDescricao() + " com " + descricao);
                if (categoria.getDescricao().contains(descricao)) {
                    return categoria.getId();
                }
            }
        }
        return -1; // Retorna -1 se a categoria não for encontrada
    }
    
    public void salvarTransacao(Transacao transacao) {
        homeRepository.saveTransacao(transacao);
    }
}
